package com.gnahz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author 张伟洁
 * Date:2024-01-14-19:52
 * @create 忆项目(小白)
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.gnahz.mapper")
public class StartTApp {
    public static void main(String[] args) {
        SpringApplication.run(StartTApp.class,args);
    }
}
