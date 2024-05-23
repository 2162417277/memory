package com.gnahz.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * @Author 张伟洁
 * Date:2024-03-17-14:42
 * @create 忆项目(小白)
 */
@Data
public class GrowReq {
    @ApiModelProperty(value = "给未来写一封信主题")
    private String growTheme;
    @ApiModelProperty(value = "给未来一封信的内容")
    private String growContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "发送（未来接收）时间")
    private Date growNewTime;
    @ApiModelProperty(value = "邮件地址")
    private String growMail;
    @ApiModelProperty(value = "接受者姓名（可匿名）")
    private String readName;
}
