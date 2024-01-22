package com.gnahz.config;



import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.support.collections.RedisProperties;

/**
 * @Author 张伟洁
 * Date:2024-01-22-19:27
 * @create 忆项目(小白)
 */
@Configuration
public class RedissonConfig {
//    @Autowired
//    private RedisProperties redisProperties;
//
//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
//                .setPassword(redisProperties.getPassword())
//                .setDatabase(redisProperties.getDatabase());
//        return Redisson.create(config);
//    }
}
