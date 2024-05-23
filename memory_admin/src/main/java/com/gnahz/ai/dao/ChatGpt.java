package com.gnahz.ai.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author 张伟洁
 * Date:2024-05-24-21:26
 * @create 忆项目(小白)
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spark-client")
public class ChatGpt {
    private String appid;
    private String apiKey;
    private String apiSecret;
}
