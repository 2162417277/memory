package com.gnahz.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.gnahz.api.CommonResult;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;


/**
 * @Author 张伟洁
 * Date:2024-01-04-22:02
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/admin")
@Api(value = "查询所有用户")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    /**
     * 注册用户信息
     * @return
     */
    @ApiOperation(value = "注册用户")
    @RequestMapping(value = "/user/insert",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<User> UserInsert(@Validated @RequestBody User user){
        User adminUser = userService.UserInsert(user);
        if (adminUser == null) {
            return CommonResult.loginhasfailed();
        }
        return CommonResult.success(adminUser);
    }

    /**
     * 用户登录(待完善token JWT)
     * http://localhost:9999/admin/user/logOn?userName=李四&&password=1234567
     * @param userName
     * @param password
     * @return
     */
    @ApiOperation("用户登录")
    @RequestMapping(value = "/user/logOn",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult UserLogOn(@PathParam("userName")String userName,
                                  @PathParam("password")String password){
        HashMap<String, String> logOn = userService.selectPasswordByName(userName, password);
        //如果为空
        if(logOn == null){
            //说明用户名或密码错误，返回一个验证失败的结果，提示"用户名或密码错误"
            return CommonResult.validateFailed("用户名或密码错误");
        }
        //说明用户名和密码匹配成功，返回一个成功的结果，将logOn作为数据返回
        return CommonResult.success(logOn);
    }




    /**
     * 根据id返回用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据用户id返回数据")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> userId(@PathVariable Integer id){
        User user = userMapper.selectById(id);
        return CommonResult.success(user);
    }
}
