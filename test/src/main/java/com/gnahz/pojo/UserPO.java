package com.gnahz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-01-14-19:52
 * @create 忆项目(小白)
 */
@Data
@TableName("sys_user")
public class UserPO {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private Integer age;

    private BigDecimal height;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
