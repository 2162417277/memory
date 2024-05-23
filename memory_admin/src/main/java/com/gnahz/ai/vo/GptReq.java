package com.gnahz.ai.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 张伟洁
 * Date:2024-05-24-21:24
 * @create 忆项目(小白)
 */
@Getter
@Setter
public class GptReq {
    @ApiModelProperty("输入传给GPT的内容")
    private String message;
}
