package com.gnahz.controller;

import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-01-04-22:02
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/admin/user")
@Api(value = "查询所有用户")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userId",method = RequestMethod.GET)
    public User userId(){
        //return CommonResult.success(JSONUtil.toJsonStr(result));hutool工具返回json对象
        return null;
    }

}
