package com.gnahz.config.component;

import com.gnahz.api.CommonResult;
import com.gnahz.config.SecureIgnoreUrlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-03-17-21:54
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/test/test")
@Slf4j
public class Controller {

    @Autowired
    private SecureIgnoreUrlsConfig secureIgnoreUrlsConfig;


    @PostMapping("/test")
    public void test(){
       log.info("读取yml的secureIgnoreUrlsConfig对象:{}",secureIgnoreUrlsConfig);
       for (String url : secureIgnoreUrlsConfig.getUrls()){
           System.out.println(url);
       }
    }
}
