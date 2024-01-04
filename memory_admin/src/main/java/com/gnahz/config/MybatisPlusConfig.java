package com.gnahz.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author 张伟洁
 * Date:2024-01-04-19:07
 * @create 忆项目(小白)
 * mybatis-plus配置类
 */
@MapperScan("com.gnahz.mapper.*")//扫描mapper文件夹
@EnableTransactionManagement//开启事务
@Configuration//配置类
public class MybatisPlusConfig {

    /**
     * 防止sql注入和逻辑删除组件
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
