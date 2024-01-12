package com.gnahz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-01-12-19:46
 * @create 忆项目(小白)
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class SecureIgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();
}
