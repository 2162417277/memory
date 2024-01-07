package com.gnahz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:16
 * @create 忆项目(小白)
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "grow")
@ApiModel(value = "com.gnahz.pojo.Grow",description = "写给未来的信/写给未来别人的信")
public class Grow implements Serializable {
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Integer growId;

    @ApiModelProperty(value = "主题")
    @TableField(value = "grow_theme")
    private String growTheme;

    @ApiModelProperty(value = "内容")
    @TableField(value = "grow_content")
    private String growContent;

    @ApiModelProperty(value = "视频/图片")
    @TableField(value = "grow_video")
    private String growVideo;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "grow_old_time")
    private Date growOldTime;

    @ApiModelProperty(value = "发送（未来接收）时间")
    @TableField(value = "grow_new_time")
    private Date growNewTime;

    @ApiModelProperty(value = "关联User表的id")
    @TableField(value = "grow_user_id")
    private Integer growUserId;

    @ApiModelProperty(value = "邮件地址")
    @TableField(value = "grow_mail")
    private String growMail;

    @ApiModelProperty(value = "接收人手机号")
    @TableField(value = "grow_telephone")
    private String growTelephone;

    @ApiModelProperty(value = "写者姓名（可匿名）")
    @TableField(value = "write_name")
    private String writeName;

    @ApiModelProperty(value = "接受者姓名（可匿名）")
    @TableField(value = "read_name")
    private String readName;

    @ApiModelProperty(value = "逻辑字段 0 未删, 1 删除")
    @TableField(value = "grow_logic")
    @TableLogic
    private Integer growLogic;
}
