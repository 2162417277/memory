package com.gnahz.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 张伟洁
 * Date:2024-01-21-22:42
 * @create 忆项目(小白)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAndPsVo {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String password;

}
