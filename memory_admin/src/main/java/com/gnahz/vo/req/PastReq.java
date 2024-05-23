package com.gnahz.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 张伟洁
 * Date:2024-03-17-13:51
 * @create 忆项目(小白)
 */
@Data
public class PastReq {
    @ApiModelProperty(value = "给过去一封信主题")
    private String pastTheme;
    @ApiModelProperty(value = "给过去一封信的内容")
    private String pastContent;
}
