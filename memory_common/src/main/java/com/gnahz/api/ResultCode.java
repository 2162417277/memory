package com.gnahz.api;

/**
 * @Author 张伟洁
 * Date:2024-01-05-11:36
 * @create 忆项目(小白)
 * 枚举了一些常用的API操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200,"操作成功"),
    LOGIN(201,"登录成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(406,"参数检验失败"),
    UNAUTHORIZED(401,"暂未登录或sess已经过期"),
    FORBIDDEN(403,"没有相关权限"),
    LOGINHASFAILED(404,"账号重复"),
    FREQUENT(405,"请求过于频繁,请稍后再试!" ),
    UNKNOWN(9999,"未知异常,请联系管理员"),
    ;



    private Long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
