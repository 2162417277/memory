package com.gnahz.api;



/**
 * @Author 张伟洁
 * Date:2024-01-05-11:31
 * @create 忆项目(小白)
 * 通用返回对象（统一）
 */
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    /**
     * protected可以被同一个包中的其他类访问，也可以被不同包中的子类访问。但是，它们不能被其他包中的非子类访问
     * 无参
     */
    protected CommonResult(){

    }

    /**
     * 有参构造
     * @param code
     * @param message
     * @param data
     */
    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param message 提示信息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),message,data);
    }


    /**n
     * 登录成功返回结果
     * @param <T>
     * @param data 获取的数据
     * @return
     */
    public static <T> CommonResult<T> successLogin(T data){
        return new CommonResult<>(ResultCode.LOGIN.getCode(),ResultCode.LOGIN.getMessage(),data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode,String message){
        return new CommonResult<T>(errorCode.getCode(),message,null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),message,null);
    }

    /**
     * 失败返回结果
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(){
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message,null);
    }

    /**
     * 未登录返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(T data){
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(T data){
        return new CommonResult<T>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), data);
    }

    /**
     * 账号重复
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> loginhasfailed() {
        return new CommonResult<T>(ResultCode.LOGINHASFAILED.getCode(),ResultCode.LOGINHASFAILED.getMessage(), null);
    }




    /**
     * get set 方法
     * @return
     */
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
