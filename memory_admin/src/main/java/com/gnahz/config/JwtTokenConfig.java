package com.gnahz.config;

import com.gnahz.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 张伟洁
 * Date:2024-01-13-20:39
 * @create 忆项目(小白)
 */
@Configuration
public class JwtTokenConfig {

    @Bean
    public JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }
}
