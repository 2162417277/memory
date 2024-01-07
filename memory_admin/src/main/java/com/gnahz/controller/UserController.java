package com.gnahz.controller;


import com.gnahz.api.CommonResult;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
