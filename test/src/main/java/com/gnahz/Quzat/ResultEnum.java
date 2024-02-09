package com.gnahz.Quzat;

/**
 * @Author 张伟洁
 * Date:2024-02-01-16:48
 * @create 忆项目(小白)
 */
public enum ResultEnum {

    SUCCESS(200,"请求成功"),
    UN_KNOWN_ERROR(-1, "未知错误"),

    PARAM_NULL_ERROR(10001,"参数为空"),

    PERMISSION_ACCESS_DENIED(50001, "permission access denied");


    private int code;
    private String name;

    private ResultEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static ResultEnum getNameByCode(int code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum;
            }
        }
        return null;
    }
}