package com.gnahz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @Author 张伟洁
 * Date:2024-01-28-21:57
 * @create 忆项目(小白)
 */

@Configuration
public class MailConfig {

    @Value("${mail.host}")
    private String host;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.port}")
    private int port;
    @Value("${mail.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setPort(port);
        sender.setHost(host);
        sender.setUsername(username);
        sender.setPassword(password);
        return sender;
    }
}
