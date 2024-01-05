package com.gnahz.exception;

import com.gnahz.api.IErrorCode;

/**
 * @Author 张伟洁
 * Date:2024-01-05-12:46
 * @create 忆项目(小白)
 * 断言处理类,用于抛出各种API异常（用于在测试或断言中检查条件是否满足，如果不满足则抛出异常，以便进行错误处理或调试）
 */
public class Asserts {
    /**
     * 当调用fail(String message)方法时，它会将传入的字符串参数作为错误消息，并创建一个新的ApiException对象，然后抛出该异常
     * @param message
     */
    public static void fail(String message){
        throw new ApiException(message);
    }

    /**
     * 当调用fail(IErrorCode errorCode)方法时，它会将传入的IErrorCode类型的错误代码作为错误消息，并创建一个新的ApiException对象，然后抛出该异常
     * @param errorCode
     */
    public static void fail(IErrorCode errorCode){
        throw new ApiException(errorCode);
    }
}
