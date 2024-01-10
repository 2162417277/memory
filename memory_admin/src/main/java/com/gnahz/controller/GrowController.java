package com.gnahz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.gnahz.api.CommonPage;
import com.gnahz.api.CommonResult;
import com.gnahz.pojo.Grow;
import com.gnahz.service.GrowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 张伟洁
 * Date:2024-01-10-18:22
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/admin/grow")
@Api(value = "写给未来")
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
    @ApiOperation("用户写给未来的信(所有)")
    @RequestMapping(value = "/queryGrow/{id}",method = RequestMethod.GET)
    public CommonResult<CommonPage> queryGrow(@PathVariable Integer id,
                                    @RequestParam(value = "pageName",defaultValue = "1")Integer pageName,
                                    @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize){
        //根据给定的用户ID、页码和每页的大小，查询该用户的记录，并将查询结果以分页的形式返回
        Page page = growService.queryGrow(id, pageName, pageSize);
        //将查询结果封装成一个CommonPage对象，并使用CommonResult.success()方法将其包装成一个成功的响应。最后，返回这个响应
        return CommonResult.success(CommonPage.restPage(page));
    }

}
