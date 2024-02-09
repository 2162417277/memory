package com.gnahz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gnahz.domin.MyUserDetails;
import com.gnahz.pojo.User;
import com.gnahz.vo.UserEnrollVo;

import java.util.HashMap;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:39
 * @create 忆项目(小白)
 */
public interface UserService extends IService<User>{

    /**
     * 注册用户
     * @param user
     * @return
     */
    User UserInsert(UserEnrollVo user);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    User selectPasswordByName(String userName, String password);


    User getAdminByUsername(String username);

    /**
     * 获得当前用户
     * @param username
     * @return
     */
    MyUserDetails loadUserByUsername(String username);
}
