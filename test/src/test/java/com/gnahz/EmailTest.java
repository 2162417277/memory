package com.gnahz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @Author 张伟洁
 * Date:2024-01-28-22:11
 * @create 忆项目(小白)
 */
@SpringBootTest
public class EmailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from = null;

    @Test
    void emilaTest(){
        SimpleMailMessage message = new SimpleMailMessage();
        //1.发送邮件人
        message.setFrom(from);
        //2.收信人
        message.setTo("629283068@qq.com");
        //3.标题
        message.setSubject("【今日提醒】");
        message.setText("今天记得吃早餐、午餐、晚餐");
        mailSender.send(message);
    }
}
