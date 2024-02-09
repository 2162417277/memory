package com.gnahz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gnahz.api.CommonPage;
import com.gnahz.api.CommonResult;
import com.gnahz.common.RateLimiting;
import com.gnahz.pojo.Grow;
import com.gnahz.pojo.User;
import com.gnahz.service.GrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author 张伟洁
 * Date:2024-01-10-18:22
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/admin/grow")
@Api(tags = "写给未来")
public class GrowController {

    @Autowired
    GrowService growService;

    /**
     * http://localhost:9999/admin/grow/queryGrow/1?pageName=1&pageSize=3
     * 未来信（查询）
     * @param id
     * @param pageName
     * @param pageSize
     * @return
     */
    @ApiOperation("查询用户写给未来的信(所有)")
    @RequestMapping(value = "/queryGrow/{id}",method = RequestMethod.GET)
    public CommonResult<CommonPage> queryGrow(@PathVariable Integer id,
                                    @RequestParam(value = "pageName",defaultValue = "1")Integer pageName,
                                    @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize){
        //根据给定的用户ID、页码和每页的大小，查询该用户的记录，并将查询结果以分页的形式返回
        Page page = growService.queryGrow(id, pageName, pageSize);
        //将查询结果封装成一个CommonPage对象，并使用CommonResult.success()方法将其包装成一个成功的响应。最后，返回这个响应
        return CommonResult.success(CommonPage.restPage(page));
    }


    /**
     * 写给未来的信
     * 该对象通过请求体（RequestBody）传递。在方法内部，使用了@Validated注解来对请求参数进行校验，确保其符合预期的格式和约束条件
     * 这段代码中的@Validated注解表示对传入的Grow对象进行验证，确保其符合预期的数据格式和约束条件。
     * 而@RequestBody注解表示将请求体中的数据绑定到方法参数上
     * 限流时间大概4分钟，使用令牌桶算法
     * @param grow
     * @return
     */
    @ApiOperation("写给未来的信")
    @RateLimiting(key = "growInsert", permitsPerSecond = 1, timeout = 500, timeunit = TimeUnit.MILLISECONDS,msg = "使用太频繁，请稍后再试！")
    //@RateLimiting(key = "growInsert", permitsPerSecond = 2.0/500, timeout = 30000, timeunit = TimeUnit.MINUTES,msg = "使用太频繁，请四分钟后再试！")
    @RequestMapping(value = "/growInsert",method = RequestMethod.POST)
    public CommonResult<Grow> growInsert(@Validated @RequestBody Grow grow){
        //调用growService.GrowInsert(grow, userId)方法将传入的Grow对象和当前用户的ID一起插入到数据库中。该方法会返回插入后的Grow对象，将其存储在变量insert中
        Grow insert = growService.GrowInsert(grow);
        //使用CommonResult.success(insert)方法将插入后的Grow对象包装成一个成功的CommonResult对象，并将其作为方法的返回值
        return CommonResult.success(insert);
    }

    /**
     * 根据条件修改信息（可修改[主题,内容,视频\图片,发送时间,邮件地址,手机号,写者姓名,发送者姓名]）
     * @param grow
     * @return
     */
    @ApiOperation("修改操作")
    @RequestMapping(value = "/growUpdate",method = RequestMethod.POST)
    public CommonResult<Grow> growUpdate(@Validated @RequestBody Grow grow){
        //前端传入一个表单只要不为空或null那么字段就进行修改
        Grow growUpdate = growService.growUpdate(grow);
        return CommonResult.success(growUpdate);
    }

}
