package com.gnahz.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.exception.Asserts;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:38
 * @create 忆项目(小白)
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public User UserInsert(User user) {
        User adminUser = new User();
        //将参数对象的属性复制到新创建的adminUser对象中
        BeanUtils.copyProperties(user,adminUser);
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
        System.out.println("添加成功");
        //将User对象插入数据库
        userMapper.insert(adminUser);
        //返回新创建的User对象
        return adminUser;
    }

    /**
     * 用户登录
     * 这个方法的主要作用是根据用户名和密码验证用户身份，并将查询结果以键值对的形式返回
     * @param userName
     * @param password
     * @return
     */
    @Override
    public HashMap<String, String> selectPasswordByName(String userName, String password) {
        //创建一个空的HashMap对象logOn，用于存储查询结果
        HashMap<String, String> logOn = new HashMap<>();
        //使用try语句块捕获可能出现的异常。
        try {
            //传入用户名返回用户密码
            String userAndPassword = userMapper.selectPasswordByName(userName);
            //比较用户输入的密码password与从数据库中查询到的用户密码userAndPassword是否相等
            if (!userAndPassword.equals(password)) {
                //如果不相等，则抛出断言错误，提示"密码不正确
                //Asserts.fail("密码不正确");
                return null;
            }
            //如果密码匹配，将用户名和密码添加到logOn中
            logOn.put("userName",userName);
            logOn.put("password",password);
        } catch (Exception e) {
            //如果在执行过程中出现异常，捕获该异常，并抛出断言错误，提示"登录异常"以及异常信息
            /**
             * Asserts.fail("密码不正确");+ Asserts.fail("登录异常:" + e.getMessage());要配合使用
             * 登录异常:密码不正确
             */
            Asserts.fail("登录异常:" + e.getMessage());
        }
        //返回查询结果logOn
        return logOn;
    }
}
