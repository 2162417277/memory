package com.gnahz.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 张伟洁
 * Date:2024-01-31-13:48
 * @create 忆项目(小白)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEnrollVo {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "第一次密码")
    private String password;
    @ApiModelProperty(value = "第二次密码")
    private String oldPassword;
}
