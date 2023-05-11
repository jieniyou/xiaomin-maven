package com.xiaomin.response;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: xm-login-background
 * @Date_Time: 2022/10/11 9:18
 */
public class Result<T> {
    /**
     * 200 - 请求成功
     * 301 - 资源（网页等）被永久转移到其它URL
     * 404 - 请求的资源（网页等）不存在
     * 500 - 内部服务器错误
     */
    private static final Integer SUCCESS_CODE=200;
    private static final String SUCCESS_MESSAGE="请求成功";
    private static final Integer FAIL_CODE=404;
    private static final String FAIL_MESSAGE="请求失败";
    private static final Integer ERROR_CODE=500;
    private static final String ERROR_MESSAGE="后端异常";

    private int code;
    private String message;
    private T data;

    /**
     * 请求返回成功
     * @return 返回给前端数据信息
     * @param <T> 返回给前端的数据
     */
    public static  <T> Result<T> ok(){
        return new Result<>(SUCCESS_CODE,SUCCESS_MESSAGE,null);
    }
    public static <T> Result<T> ok(String message){
        return new Result<>(SUCCESS_CODE,message,null);
    }
    public static <T> Result<T> ok(T data){
        return new Result<>(SUCCESS_CODE,SUCCESS_MESSAGE,data);
    }
    public static <T> Result<T> ok(String message,T data){
        return new Result<>(SUCCESS_CODE,message,data);
    }

    /**
     * 请求返回失败
     * @return 返回给前端数据信息
     * @param <T> 返回给前端的数据
     */
    public static <T> Result<T> no(){
        return new Result<>(FAIL_CODE,FAIL_MESSAGE,null);
    }
    public static <T> Result<T> no(String message){
        return new Result<>(FAIL_CODE,message,null);
    }
    public static <T> Result<T> no(T data){
        return new Result<>(FAIL_CODE,FAIL_MESSAGE,data);
    }
    public static <T> Result<T> no(String message,T data){
        return new Result<>(FAIL_CODE,message,data);
    }
    /**
     * 后端异常
     * @return 返回给前端数据信息
     * @param <T> 返回给前端的数据
     */
    public static <T> Result<T> error(){
        return new Result<>(ERROR_CODE,ERROR_MESSAGE,null);
    }
    public static <T> Result<T> error(String message){
        return new Result<>(ERROR_CODE,message,null);
    }
    public static <T> Result<T> error(T data){
        return new Result<>(ERROR_CODE,ERROR_MESSAGE,data);
    }
    public static <T> Result<T> error(String message,T data){
        return new Result<>(ERROR_CODE,message,data);
    }

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
