package com.gnahz;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gnahz.mapper.SysUserMapper;
import com.gnahz.pojo.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @Author 张伟洁
 * Date:2024-01-25-20:33
 * @create 忆项目(小白)
 */
@SpringBootTest
public class StartAppTest {

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void md5AndUser(){
        SysUser sysUser = new SysUser();
        UpdateWrapper<SysUser> wrapper = new UpdateWrapper<>();
        String username = "888";
        String password = "123456";
        String encodePassword = BCrypt.hashpw(password,null);
        wrapper.lambda().eq(SysUser::getUserName,username).set(SysUser::getPassword,encodePassword);
        int result = sysUserMapper.update(sysUser, wrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);

    }
}
