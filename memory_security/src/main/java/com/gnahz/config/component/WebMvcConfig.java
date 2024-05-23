package com.gnahz.config.component;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 张伟洁
 * Date:2024-03-17-21:36
 * @create 忆项目(小白)
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 静态资源放行
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/web/");

        // 对swaggger静态资源放行
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}