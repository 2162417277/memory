package com.gnahz.email.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.util.Calendar;

/**
 * @Author 张伟洁
 * Date:2024-02-20-10:07
 * @create 忆项目(小白)
 */
@Slf4j
@Component
public class NetEaseServiceImpl {
    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发指定模板邮箱123网易云的邮箱
     * @param time 时间
     * @param title 主题
     * @param userEmail 邮箱
     */
    public void CurrentMail(String time,String title,String userEmail){
        SimpleMailMessage messageMail = new SimpleMailMessage();
        messageMail.setFrom(username);
        messageMail.setTo(userEmail);
        String titleMail = "时空邮寄-提交以被接收";
        messageMail.setSubject(titleMail);
        String text = Text(time, title);
        messageMail.setText(text);
        try{
            mailSender.send(messageMail);
            log.info("邮件发送成功");
        }catch (MailException e){
            log.error("发送邮件失败发生异常",e);
        }
    }

    public String Text(String time,String title){
        //根据空格分割日期
        String[] currentSplitTime = time.split(" ");
        //获取空格前的日期
        String currentTime = currentSplitTime[0];
        //获取日期的年份
        String futureTime = currentTime.substring(0, 4);
        //获取当前时间的Calendar对象
        Calendar calendar = Calendar.getInstance();
        //在当前年份上加上100年
        calendar.add(Calendar.YEAR,100);
        //获取加100年后的年份
        int year = calendar.get(Calendar.YEAR);
        return "你好,时空邮寄已经寄出，我们将在你选择的未来某一天,投递您的信件。\n" +
                "您无需担心你的邮件遗漏,时空邮寄系统存在于时间长河当中,会准时在那天略过你的身边,并且把信件带给给您\n" +
                "您的跨时空邮件标题为:《"+title+"》时空邮寄系统将会跨时空携带信件前往"+currentTime+"向未来的你投递信件\n" +
                "嘿！别尝试向我们打听未来的秘密，我们得遵循《"+year+"年时空法》在各个时空之间旅行。" +
                "如果我们泄露了任何信息，时空邮寄人员可能会被时空执法者逮捕的，所以必须小心谨慎！但是别担心，" +
                "未来的你肯定会非常、非常、非常美好！哦，对了！未来的你让我们提醒你：“每天都要保持愉快的心情哦！”" +
                "\n" +
                "\n" +
                "By @ Copyright 时空邮寄 "+futureTime+"~未来";
    }
}
