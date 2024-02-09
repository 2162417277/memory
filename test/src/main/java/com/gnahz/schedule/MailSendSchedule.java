package com.gnahz.schedule;

import com.gnahz.domain.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-01-28-22:00
 * @create 忆项目(小白)
 */

@Slf4j
@Component
public class MailSendSchedule {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Scheduled(cron = "${mail.cron}")
    public void sendMail(){
        log.info("-------------->进入邮件定时发送任务");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时");
        List<Content> infoList = new ArrayList<>();
        infoList.add(new Content("小兰","女","听音乐"));
        infoList.add(new Content("小明","男","打篮球"));

        // TODO 需要自己后续配置邮件内容、收件人、抄送人等信息
        String text = getText(infoList);
        String[] receiver = {"629283068@qq.com"};
        String[] cc = {"你好"};

        // 发送html格式邮件
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            mimeMessageHelper.setFrom(mailSender.getUsername());
            String currentTime = sdf.format(new Date());
            mimeMessageHelper.setSubject(currentTime+"邮件发送测试");
            if (receiver.length == 0 || cc.length == 0) {
                throw new MessagingException("收件人或抄送人为空！！！");
            }
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
            log.info("-------------->邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("-------------->邮件发送失败！！！{}",e.toString());
        } finally {
            log.info("-------------->邮件定时发送任务结束");
        }
    }

    // 获取邮件内容
    private String getText(List<Content> infoList){
        StringBuilder message = new StringBuilder();
        message.append("<table border=\"1\" cellpadding=\"1\"; cellspacing=\"1\">");
        message.append("<tr>");
        message.append("<td style='background-color:blue'>姓名</td>");
        message.append("<td style='background-color:blue'>性别</td>");
        message.append("<td style='background-color:blue'>爱好</td>");
        message.append("</tr>");
        infoList.stream().forEach((content)->{
            message.append("<tr>");
            message.append("<td>"+content.getName()+"</td>");
            message.append("<td>"+content.getSex()+"</td>");
            message.append("<td>"+content.getHobby()+"</td>");
            log.info(content.getName()+"+"+content.getSex()+"+"+content.getHobby());
            message.append("</tr>");
        });
        message.append("</table>");
        return message.toString();
    }
}