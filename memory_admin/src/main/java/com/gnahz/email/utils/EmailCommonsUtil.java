package com.gnahz.email.utils;

import com.gnahz.email.dto.SmtpProperties;
import lombok.AllArgsConstructor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @Author 张伟洁
 * Date:2024-01-28-16:41
 * @create 忆项目(小白)
 */
@AllArgsConstructor
@Component
public class EmailCommonsUtil {
    private static final Log logger = LogFactory.getLog(EmailCommonsUtil.class);

    private final SmtpProperties emailProperties;

    /**
     * 邮件发送
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param contentIsHtml 内容是否为html格式
     * @param toMail 收件人邮箱
     * @param ccMail 抄送人邮箱
     * @param bccMail 秘密抄送人邮箱
     * @param fileList 附件
     */
    public void sendEmail(String subject, String content,boolean contentIsHtml,
                          String[] toMail, String[] ccMail, String[] bccMail, File[] fileList)
            throws EmailException, UnsupportedEncodingException {
        HtmlEmail email = new HtmlEmail();
        // smtp服务地址
        email.setHostName(emailProperties.getHost());
        // 邮件验证
        email.setAuthentication(emailProperties.getEmail(),emailProperties.getPassword());
        // smtp端口
        email.setSmtpPort(emailProperties.getPort());
        email.setCharset("utf-8");
        // 发件人邮箱地址及昵称
        email.setFrom(emailProperties.getEmail(),emailProperties.getName());
        // 收件人邮箱
        email.addTo(toMail);
        if(!ObjectUtils.isEmpty(ccMail)){
            // 抄送人邮箱
            email.addCc(ccMail);
        }
        if(!ObjectUtils.isEmpty(bccMail)){
            // 秘密抄送人邮箱
            email.addBcc(bccMail);
        }
        // 主题
        email.setSubject(subject);
        if(contentIsHtml){
            email.setHtmlMsg(content);
        }else{
            email.setMsg(content);
        }
        // 设置附件
        if(!ObjectUtils.isEmpty(fileList)){
            for (File file : fileList) {
                EmailAttachment emailAttachment = new EmailAttachment();
                emailAttachment.setName(MimeUtility.encodeText(file.getName()));
                emailAttachment.setPath(file.getPath());
                email.attach(emailAttachment);
            }
        }
        // 发送邮件
        email.send();
        logger.info("邮件发送完成");
    }

}
