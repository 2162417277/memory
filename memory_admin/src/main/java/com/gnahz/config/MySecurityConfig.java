package com.gnahz.config;

import com.gnahz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author 张伟洁
 * Date:2024-01-22-8:21
 * @create 忆项目(小白)
 */
@Configuration
@EnableWebSecurity //启动
public class MySecurityConfig extends SecurityConfig{

    @Autowired
    private UserService userService;

    /**
     * 认证交给springSecurity
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return username ->  userService.loadUserByUsername(username);
    }

}
