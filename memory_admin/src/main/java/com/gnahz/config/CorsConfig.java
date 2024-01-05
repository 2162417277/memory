package com.gnahz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author 张伟洁
 * Date:2024-01-05-11:16
 * @create 忆项目(小白)
 * 全局跨域配置
 */
@Configuration
public class CorsConfig {

    //当前跨域请求最大有效时长.这默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    /**
     * 允许跨域调用的过滤器
     * @return
     */
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//1.设置访问源地址（允许所有域名进行跨域调用）
        corsConfiguration.setAllowCredentials(true);//允许跨域发送cookie
        corsConfiguration.addAllowedHeader("*");//2.设置访问源请求头（放行全部原始头信息）
        corsConfiguration.addAllowedMethod("*");//3.设置访问源请求方法（允许所有请求方法跨域调用）
        corsConfiguration.setMaxAge(MAX_AGE);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);//3.接口配置跨域设置
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
