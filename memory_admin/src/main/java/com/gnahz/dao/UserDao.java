package com.gnahz.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Past;
import com.gnahz.pojo.User;
import com.gnahz.vo.req.UserEnrollReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-01-13-20:25
 * @create 忆项目(小白)
 */
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    public Boolean userInsert(UserEnrollReq user, Date date, String encryptPassword) {
        User userInsert = new User();
        userInsert.setUserName(user.getUsername());
        userInsert.setPassword(encryptPassword);
        userInsert.setUserDate(date);
        userInsert.setUserLogic(0);
        int result = userMapper.insert(userInsert);
        return result > 0 ? true : false;
    }

    public int queryEqual(String username) {
        return lambdaQuery()
                .eq(User::getUserName,username)
                .count();
    }


    public Integer findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User selectPasswordByName(String username) {
        return lambdaQuery()
                .eq(User::getUserName,username)
                .one();
    }

    public User MyUserDetails(String username) {
        return lambdaQuery()
                .eq(User::getUserName,username)
                .one();
    }
}
