package com.gnahz.api;

/**
 * @Author 张伟洁
 * Date:2024-01-05-11:38
 * @create 忆项目(小白)
 * 封装API的错误码
 */
public interface IErrorCode {
    /**
     * 获取错位编号code
     * @return
     */
    long getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMessage();
}
