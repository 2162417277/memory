package com.gnahz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gnahz.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @Author 张伟洁
 * Date:2024-01-04-18:37
 * @create 忆项目(小白)
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户userName查询用户密码
     * @param userName
     * @return
     */
    String selectPasswordByName(@Param("userName") String userName);

    /**
     * 根据用户名查询id
     * @param userName
     * @return
     */
    Integer findByUsername(@Param("userName") String userName);
}
