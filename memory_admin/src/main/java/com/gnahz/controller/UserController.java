package com.gnahz.controller;


import com.gnahz.api.CommonResult;
import com.gnahz.common.utils.JwtTokenUtil;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.HashMap;

import static org.apache.http.client.methods.RequestBuilder.put;


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
    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult UserLogin(@PathParam("userName")String userName,
                                  @PathParam("password")String password){
        HashMap<String, String> logOn = userService.selectPasswordByName(userName, password);
        //如果为空
        if(logOn == null){
            //说明用户名或密码错误，返回一个验证失败的结果，提示"用户名或密码错误"
            return CommonResult.validateFailed("用户名或密码错误");
        }
        //jwtTokenUtil.generateUserNameStr(logOn.get(userName))方法来生成token。
        // 然后，将生成的token以及其他相关信息存储在一个名为tokenMap的HashMap中
        String token = jwtTokenUtil.generateUserNameStr(logOn.get(userName));
        HashMap<Object, Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        tokenMap.put("tokenHeader",tokenHeader);
        //说明用户名和密码匹配成功，返回一个成功的结果，将tokenMap作为数据返回
        //返回一个成功的结果，并将tokenMap作为数据返回。这样，客户端就可以使用这个token来进行后续的操作了
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if(refreshToken == null){
            return CommonResult.failed("token已经过期");
        }
        //创建一个HashMap对象tokenMap，并将新的refreshToken和tokenHead存储在其中。
        HashMap<Object, Object> tokenMap = new HashMap<>();
        tokenMap.put("token",refreshToken);
        tokenMap.put("tokenHead",tokenHead);
        // 最后，返回一个成功的结果，并将tokenMap作为数据传递出去
        return CommonResult.success(tokenMap);
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
