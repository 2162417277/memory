package com.gnahz.exception;

import com.gnahz.api.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 张伟洁
 * Date:2024-03-17-20:54
 * @create 忆项目(小白)
 */
@AllArgsConstructor
@Getter
public enum BusinessErrorEnum implements IErrorCode {
    //==================================common==================================
    BUSINESS_ERROR(1001, "{0}"),
    //==================================user==================================
    //==================================chat==================================
    SYSTEM_ERROR(1001, "系统出小差了，请稍后再试哦~~"),
    ;
    private Integer code;
    private String msg;


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
