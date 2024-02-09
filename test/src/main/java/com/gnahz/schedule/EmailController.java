package com.gnahz.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author 张伟洁
 * Date:2024-01-28-22:14
 * @create 忆项目(小白)
 */
@RestController
public class EmailController {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from = null;

    @PostMapping("/email/send")
    public String getByEmailSend() throws ParseException {
        SimpleMailMessage message = new SimpleMailMessage();
        String email_date = "2021-05-27 17:20:00";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =new Date();
        System.out.println("准备发送,现在时间为------------>"+sf.format(date));
        //定时主要实现就是这个，实现run方法
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //1.发送邮件人
                message.setFrom(from);
                //2.收信人
                message.setTo("1301178858@qq.com");
                //3.标题
                message.setSubject("【今日提醒】");
                message.setText("今天记得吃早餐、午餐、晚餐");
                mailSender.send(message);
                System.out.println("邮件收到时间为----------->"+email_date);
            }
        },new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(email_date));
        return "SSUCCESS";
    }
}
