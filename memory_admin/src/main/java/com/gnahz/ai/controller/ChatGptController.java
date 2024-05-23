package com.gnahz.ai.controller;

import com.gnahz.ai.server.ChatGptServer;
import com.gnahz.ai.vo.GptReq;
import com.gnahz.api.CommonResult;
import com.gnahz.api.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 张伟洁
 * Date:2024-05-23-14:03
 * @create 忆项目(小白)
 */
@RestController
@RequestMapping("/chat")
@Api(tags = "ai聊天")
public class ChatGptController {

    @Autowired
    private ChatGptServer chatGptServer;

    @PostMapping("/GPT")
    @ApiOperation("聊天功能")
    public CommonResult chatStreamTest(@RequestBody GptReq gptReq) {
        String reply = chatGptServer.QueryAi(gptReq.getMessage());
        return CommonResult.success(ResultCode.SUCCESSAI.getCode(),reply,ResultCode.SUCCESSAI.getMessage());
    }
}

