package com.gnahz.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.dao.UserDao;
import com.gnahz.domin.MyUserDetails;
import com.gnahz.exception.ApiException;
import com.gnahz.service.UserCacheService;
import com.gnahz.utils.JwtTokenUtil;
import com.gnahz.exception.Asserts;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import com.gnahz.vo.UserEnrollVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:38
 * @create 忆项目(小白)
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Lazy
    @Autowired
    UserCacheService userCacheService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDao userDao;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Transactional//MyBatis会确保在方法执行过程中对数据库的操作具有原子性、一致性、隔离性和持久性（ACID）
    @Override
    public User UserInsert(UserEnrollVo user) {
        User adminUser = new User();
        if(!user.getPassword().equals(user.getOldPassword())){
            return null;
        }
        //将参数对象的属性复制到新创建的adminUser对象中
       adminUser.setUserName(user.getUsername());
        adminUser.setPassword(user.getPassword());
        //当前时间
        Date date = DateUtil.date();
        // 设置创建时间为当前时间    输出格式化后的日期和时间字符串2024-01-07 14:26:41
        adminUser.setUserDate(date);
        //设置状态为0（表示已激活)
        adminUser.setUserLogic(0);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //这里面不要用.lambda()表达式,使用sql会报错
        //使用eq方法设置查询条件，即"user_name"字段等于adminUser对象的getUserName()方法返回的值
        wrapper.eq("user_name",adminUser.getUserName());
        //如果查询结果大于0，说明数据库中已经存在
        if(userMapper.selectCount(wrapper)>0){
            return null;
        }
        //如果查询结果为0，说明数据库中不存在与adminUser的用户名相同的用户
        String EncryptPassword = BCrypt.hashpw(adminUser.getPassword());
        adminUser.setPassword(EncryptPassword);
        //将User对象插入数据库
        userMapper.insert(adminUser);
        //返回新创建的User对象
        return adminUser;
    }

    /**
c     * 用户登录
     * 这个方法的主要作用是根据用户名和密码验证用户身份，并将查询结果以键值对的形式返回
     * @return
     */
    @Override
    public User selectPasswordByName(String username, String password) {

        //密码需要客户端加密后传递
        User user = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            user = ((MyUserDetails) userDetails).getUser();

            if (!passwordEncoder.matches(password, user.getPassword())) {
               // Asserts.fail("密码不正确");
                return null;
            }

            // 生成springsecurity的通过认证标识
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            if (!userDetails.isEnabled()) {
                Asserts.fail("帐号已被禁用");
            }
        } catch (Exception e) {
            Asserts.fail("登录异常:" + e.getMessage());
        }
        return user;
    }







    @Override
    public User getAdminByUsername(String username) {
        User user = userCacheService.getUser(username);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUserName,username);
        List<User> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            user = adminList.get(0);
            userCacheService.setUser(user);
            return user;
        }
        return null;
    }


    /**
     * 获得当前用户
     * @return
     */
    public User getCurrentMember(){
        // 标识
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails memberDetails =(MyUserDetails) authentication.getPrincipal();
        return memberDetails.getUser();
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        if(!Objects.isNull(user)){
            return new MyUserDetails(user);
        }
        throw  new ApiException("用户名或密码错误!!!");
//        User umsMember = getAdminByUsername(username);
//        if(umsMember!=null){
//            return new MyUserDetails(umsMember);
//        }
//        throw  new ApiException("用户名或密码错误!!!");
    }
}
