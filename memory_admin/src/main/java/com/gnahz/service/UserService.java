package com.gnahz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gnahz.pojo.User;

import java.util.HashMap;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:39
 * @create 忆项目(小白)
 */
public interface UserService extends IService<User> {

    /**
     * 注册用户
     * @param user
     * @return
     */
    User UserInsert(User user);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    HashMap<String, String> selectPasswordByName(String userName, String password);
}
