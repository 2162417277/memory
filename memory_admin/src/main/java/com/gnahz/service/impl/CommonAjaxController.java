package com.gnahz.service.impl;

/**
 * @Author 张伟洁
 * Date:2024-02-10-10:48
 * @create 忆项目(小白)
 */

import com.gnahz.mapper.GrowMapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

import java.util.Map;


@Service
public class CommonAjaxController {
  /*  @Value("${spring.mail.username1}")
    private String from;
    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    GrowMapper growMapper;

    public void commonEmail() {
        Grow grow = growMapper.selectById(57L);

        String userName= grow.getReadName();
        System.out.println(userName);
        String userEmail=grow.getGrowMail();
        System.out.println(userEmail);
        // 创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(userEmail);
        message.setSubject(grow.getGrowTheme());
        message.setText("时光旅行者"+userName+",您好:\n" +grow.getGrowContent());
        mailSender.send(message);
    }
*/
}


