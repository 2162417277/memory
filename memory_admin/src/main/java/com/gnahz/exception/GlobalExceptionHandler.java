package com.gnahz.exception;

import com.gnahz.api.CommonResult;
import com.gnahz.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 张伟洁
 * Date:2024-01-05-12:50
 * @create 忆项目(小白)
 * 全局异常处理类
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public CommonResult handle(ApiException e){
        if(e.getErrorCode() != null){
            //如果错误码不为空，则表示异常具有特定的错误码，因此使用该错误码作为失败的结果返回
            return CommonResult.failed(e.getErrorCode());
        }
        //如果错误码为空，则表示异常没有特定的错误码，而是使用异常的消息（getMessage()）作为失败的结果返回
        return CommonResult.failed(e.getMessage());
    }

    /**
     * 处理未知异常
     * @param e
     * @return
     */
    @ResponseBody//该方法的返回值将被直接写入HTTP响应体中
    @ExceptionHandler(value = RuntimeException.class)//指定该方法用于处理RuntimeException类型的异常
    public CommonResult handleRuntimeException(RuntimeException e){
        //使用log.error()方法记录异常信息
        log.error("运行时异常:",e);
        //返回一个通用的结果对象CommonResult，其中包含了失败的状态码ResultCode.UNKNOWN。这意味着当发生运行时异常时，该方法将返回一个未知错误的结果
        return CommonResult.failed(ResultCode.UNKNOWN);
    }

    /**
     * 处理验证异常，提取错误信息并返回一个表示验证失败的通用结果对象
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)//指定该方法用于处理MethodArgumentNotValidException类型的异常
    public CommonResult handleValidException(MethodArgumentNotValidException e){
        //方法首先通过调用e.getBindingResult()获取绑定结果对象bindingResult
        BindingResult bindingResult = e.getBindingResult();
        //声明一个字符串变量message并将其初始化为null
        String message = null;
        //判断是否存在错误
        if(bindingResult.hasErrors()){
            //存在错误
            //使用bindingResult.getFieldError()获取字段错误对象fieldError
            FieldError fieldError = bindingResult.getFieldError();
            //如果fieldError不为null，
            if(fieldError != null){
                //则将message设置为fieldError.getDefaultMessage()，即默认的错误消息
                message = fieldError.getDefaultMessage();
            }
        }
        //返回一个表示验证失败的通用结果对象，其中包含错误消息
        return CommonResult.validateFailed(message);
    }

    /**
     * 返回一个表示验证失败的通用结果对象，其中包含错误消息
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)//指定该方法用于处理BindException类型的异常
    public CommonResult handleValidException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                //即字段名和默认的错误消息的组合
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }
}
