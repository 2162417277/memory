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
@TableName(value = "user")
@ApiModel(value = "com.gnahz.pojo.User",description = "用户信息对象(账号密码表)")
public class User implements Serializable {
    /*主键自增*/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户账号")
    @TableField(value = "user_name")
    private String UserName;

    @ApiModelProperty(value = "用户密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty(value = "用户创建时间")
    @TableField(value = "user_date")
    private Date userDate;

    @ApiModelProperty(value = "逻辑删除 0 未删, 1 删除")
    @TableField(value = "user_logic")
    @TableLogic
    private Integer userLogic;
}
