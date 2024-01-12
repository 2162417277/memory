package com.gnahz.controller;

import com.gnahz.api.CommonResult;
import com.gnahz.pojo.dto.OssPolicyResult;
import com.gnahz.service.OssService;
import com.gnahz.service.impl.OssServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 张伟洁
 * Date:2024-01-12-10:01
 * @create 忆项目(小白)
 */
@Controller
@Api(tags = "OssController",description = "Oss管理")
@RequestMapping("/admin/aliyun/oss")
public class OssController {

    @Autowired
    private OssServiceImpl ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy(){
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }
}
