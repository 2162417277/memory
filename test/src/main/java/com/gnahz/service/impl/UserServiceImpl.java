package com.gnahz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.UserPO;
import com.gnahz.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author 张伟洁
 * Date:2024-01-14-19:55
 * @create 忆项目(小白)
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
}
