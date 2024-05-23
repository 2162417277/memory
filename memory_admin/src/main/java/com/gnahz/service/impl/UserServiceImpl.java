package com.gnahz.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.api.IErrorCode;
import com.gnahz.api.ResultCode;
import com.gnahz.dao.UserDao;
import com.gnahz.domin.MyUserDetails;
import com.gnahz.exception.AssertUtil;
import com.gnahz.service.UserCacheService;
import com.gnahz.utils.JwtTokenUtil;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import com.gnahz.vo.req.UserEnrollReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    public Boolean UserInsert(UserEnrollReq user) {
        if(!user.getPassword().equals(user.getOldPassword())){
            return null;
        }
        //当前时间
        // 设置创建时间为当前时间    输出格式化后的日期和时间字符串2024-01-07 14:26:41
        Date date = DateUtil.date();
        int countName = userDao.queryEqual(user.getUsername());
        //如果查询结果大于0，说明数据库中已经存在
        if(countName > 0){
            return null;
        }
        //如果查询结果为0，说明数据库中不存在与adminUser的用户名相同的用户
        String EncryptPassword = BCrypt.hashpw(user.getPassword());
        //将User对象插入数据库
        Boolean userInsert = userDao.userInsert(user, date, EncryptPassword);
        //返回新创建的User对象
        return userInsert == true ? userInsert : null;
    }

    /**
c     * 用户登录
     * 这个方法的主要作用是根据用户名和密码验证用户身份，并将查询结果以键值对的形式返回
     * @return
     */
    @Override
    public void selectPasswordByName(String username, String password) {

        //密码需要客户端加密后传递
        User user = null;
            UserDetails userDetails = loadUserByUsername(username);
            user = ((MyUserDetails) userDetails).getUser();

            User selectOne = userDao.selectPasswordByName(username);


           AssertUtil.isFalse(selectOne == null,"用户不存在");

           AssertUtil.isFalse(!passwordEncoder.matches(password, user.getPassword()),"密码不正确");
            // 生成springsecurity的通过认证标识
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            AssertUtil.isFalse(!userDetails.isEnabled(),"帐号已被禁用");
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
        User user = userDao.MyUserDetails(username);
        if(!Objects.isNull(user)){
            return new MyUserDetails(user);
        }
        AssertUtil.userNull("用户名或密码错误!!!");
        return null;
    }


}
