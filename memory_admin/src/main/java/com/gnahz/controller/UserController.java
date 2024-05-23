package com.gnahz.controller;


import com.gnahz.api.CommonResult;
import com.gnahz.common.RateLimiting;
import com.gnahz.utils.JwtTokenUtil;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import com.gnahz.vo.req.UserAndPwdReq;
import com.gnahz.vo.req.UserEnrollReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * @Author 张伟洁
 * Date:2024-01-04-22:02
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "用户信息")
public class UserController {

    @Autowired
    HttpSession session;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;


    /**
     * 注册用户信息
     * {
     *     "userName": "朱榕",
     *     "password": "213432"
     * }
     * @return
     */
    @ApiOperation("注册用户")
    @RequestMapping(value = "/public/user/insert",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<User> UserInsert(@Validated @RequestBody UserEnrollReq user){
        Boolean userInsert = userService.UserInsert(user);
        if (userInsert == null) {
            return CommonResult.loginhasfailed();
        }
        return CommonResult.success();
    }

    /**
     * 用户登录(已完善token JWT)
     * http://localhost:9999/admin/user/logOn?userName=李四&&password=1234567

     * @return
     * @PathParam("userName")String userName,
     *                                   @PathParam("password")String password
     */

    @ApiOperation("用户登录")
    @RateLimiting(key = "userLogin", permitsPerSecond = 1, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "登录太频繁，请稍后再试！")
    @RequestMapping(value = "/public/user/login",method = RequestMethod.POST)
    public CommonResult UserLogin(@Valid @RequestBody UserAndPwdReq userAndPwdReq){
       userService.selectPasswordByName(userAndPwdReq.getUsername(), userAndPwdReq.getPassword());
        //jwtTokenUtil.generateUserNameStr(logOn.get(userName))方法来生成token。
        // 然后，将生成的token以及其他相关信息存储在一个名为tokenMap的HashMap中
        String token = jwtTokenUtil.generateUserNameStr(userAndPwdReq.getUsername());
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        tokenMap.put("tokenHeader",tokenHeader);
        //说明用户名和密码匹配成功，返回一个成功的结果，将tokenMap作为数据返回
        //返回一个成功的结果，并将tokenMap作为数据返回。这样，客户端就可以使用这个token来进行后续的操作了
        return CommonResult.successLogin(tokenMap);
    }




    /**
     * 根据id返回用户信息
     * @param id
     * @return
     */
    @ApiOperation("根据用户id返回数据")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> userId(@PathVariable Integer id){
        User user = userMapper.selectById(id);
        return CommonResult.success(user);
    }


}
