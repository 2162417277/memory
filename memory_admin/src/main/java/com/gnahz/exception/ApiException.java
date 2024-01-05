package com.gnahz.exception;

import com.gnahz.api.IErrorCode;

/**
 * @Author 张伟洁
 * Date:2024-01-05-12:39
 * @create 忆项目(小白)
 * 自定义API异常
 */
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    /**
     * super:调用父类的方法
     * this:引用当前对象的属性和方法
     * @param errorCode
     */
    public ApiException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * super(message) = 通过e.getMessage()获取异常的消息内容，然后将其打印出来
     * @param message
     */
    public ApiException(String message){
        super(message);
    }

    /**
     * 通过使用super(cause)语句，可以将传入的cause参数传递给父类的构造函数，
     * 以便在创建ApiException对象时设置异常的原因
     * @param cause
     */
    public ApiException(Throwable cause){
        super(cause);
    }

    public ApiException(String message,Throwable cause){
        super(message,cause);
    }

    public IErrorCode getErrorCode(){
        return errorCode;
    }
}
