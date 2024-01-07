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
@TableName(value = "past")
@ApiModel(value = "com.gnahz.pojo.Past",description = "记录以前日记")
public class Past implements Serializable {
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Integer pastId;

    @ApiModelProperty(value = "主题")
    @TableField(value = "past_theme")
    private String pastTheme;

    @ApiModelProperty(value = "内容")
    @TableField(value = "past_content")
    private String pastContent;

    @ApiModelProperty(value = "视频/图片")
    @TableField(value = "past_video")
    private String pastVideo;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "past_old_time")
    private Date pastOldTime;

    @ApiModelProperty(value = "关联user表id（编号）")
    @TableField(value = "past_user_id")
    private Integer pastUserId;

    @ApiModelProperty(value = "逻辑字段, 0 未删, 1 删除")
    @TableField(value = "past_logic")
    @TableLogic
    private Integer pastLogic;
}
