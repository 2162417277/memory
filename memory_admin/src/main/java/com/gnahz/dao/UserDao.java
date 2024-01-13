package com.gnahz.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 张伟洁
 * Date:2024-01-13-20:25
 * @create 忆项目(小白)
 */
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {

    @Autowired
    UserMapper userMapper;

    public void UserInsert(User user) {
    }
}
