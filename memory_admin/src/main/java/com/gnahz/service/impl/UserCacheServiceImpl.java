package com.gnahz.service.impl;

import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.RedisService;
import com.gnahz.service.UserCacheService;
import com.gnahz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author 张伟洁
 * Date:2024-01-22-10:11
 * @create 忆项目(小白)
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserMapper userMapper;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_ADMIN;



    @Override
    public void delAdmin(Long adminId) {
        User user = userService.getById(adminId);
        if(user != null){
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + user.getUserName();
            redisService.del(key);
        }
    }

    @Override
    public User getUser(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (User) redisService.get(key);
    }

    @Override
    public void setUser(User admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUserName();
        redisService.set(key,admin,REDIS_EXPIRE);
    }
}
