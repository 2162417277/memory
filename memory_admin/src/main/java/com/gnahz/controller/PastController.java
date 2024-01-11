package com.gnahz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gnahz.api.CommonPage;
import com.gnahz.api.CommonResult;
import com.gnahz.pojo.Past;
import com.gnahz.pojo.User;
import com.gnahz.service.PastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 张伟洁
 * Date:2024-01-11-8:35
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/admin/past")
@Api(value = "遗憾的信息")
public class PastController {

    @Autowired
    PastService pastService;

    @ApiOperation("查询写给死去的自己(所有)")
    @RequestMapping(value = "/queryPast/{id}",method = RequestMethod.GET)
    public CommonResult<CommonPage> queryPast(@PathVariable Integer id,
                                              @RequestParam(value = "pageName",defaultValue = "1")Integer pageName,
                                              @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize){
        //根据给定的用户ID、页码和每页的大小，查询该用户的记录，并将查询结果以分页的形式返回
        Page page = pastService.queryPast(id, pageName, pageSize);
        //将查询结果封装成一个CommonPage对象，并使用CommonResult.success()方法将其包装成一个成功的响应。最后，返回这个响应
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 给以前的自己一封信
     * @param past
     * @return
     */
    @ApiOperation("给以前的自己一封信")
    @RequestMapping(value = "/pastInset",method = RequestMethod.POST)
    public CommonResult<Past> PastInsert(@Validated @RequestBody Past past){
        //创建一个user对象
        User user = new User();
        //获取user对象的id值
        Integer userId = user.getUserId();
        //传入past对象和user的id值
        Past insert = pastService.PastInsert(past, userId);
        //使用CommonResult.success(insert)方法将插入后的Past对象包装成一个成功的CommonResult对象，并将其作为方法的返回值
        return CommonResult.success(insert);
    }

    /**
     * 用户添加好的单表单信息修改（可修改信息为[主题pastTheme,内容pastContent,视频/图片pastVideo,]）
     * @param past
     * @return
     */
    @ApiOperation("根据条件修改信息（过去）")
    @RequestMapping(value ="/pastUpdate",method = RequestMethod.POST)
    public CommonResult<Past> pastUpdate(@Validated @RequestBody Past past){
        //前端传入一个表单只要不为空或null那么字段就进行修改
        Past pastUpdate = pastService.pastUpdate(past);
        return CommonResult.success(pastUpdate);
    }


}
