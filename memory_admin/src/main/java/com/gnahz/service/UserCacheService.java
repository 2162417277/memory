package com.gnahz.service;

import com.gnahz.pojo.User;

/**
 * @Author 张伟洁
 * Date:2024-01-22-10:10
 * @create 忆项目(小白)
 * 后台用户缓存管理Service
 */
public interface UserCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);


    /**
     * 获取缓存后台用户信息
     */
    User getUser(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setUser(User admin);
}
