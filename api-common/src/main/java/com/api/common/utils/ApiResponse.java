package com.api.common.utils;
/*
* 统一响应类。
* */
public class ApiResponse<T> {
    private int code; // 状态码

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

    private String message; // 消息
    private T data; // 返回的数据

    public ApiResponse() {

    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 定义常用静态方法
     */
    public static <T> ApiResponse ofSuccess(T data) {
        return new ApiResponse<T>(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    public static <T> ApiResponse ofSuccess() {
        return new ApiResponse<T>(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), null);
    }

    public static <T> ApiResponse ofError() {
        return new ApiResponse<T>(Status.ERROR.getCode(), Status.ERROR.getStandardMessage(), null);
    }

    public static <T> ApiResponse ofError(T data) {
        return new ApiResponse<T>(Status.ERROR.getCode(), Status.ERROR.getStandardMessage(), data);
    }

    public static <T> ApiResponse ofMessage(int code, String message) {
        return new ApiResponse<T>(code, message, null);
    }

    public static <T> ApiResponse ofStatus(Status status) {
        return new ApiResponse<T>(status.getCode(), status.getStandardMessage(), null);
    }

    /**
     * 定义状态枚举
     */
    public enum Status {
        SUCCESS(200, "OK"),
        ERROR(1, "fail"),
        DEFINE(-1,"数据信息不全"),
        USERNAME_EXIST(-2,"用户名已存在"),
        DELETE(-2,"数据删除失败"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "page not found"),
        INTERNAL_SERVER_ERROR(500, "server internal error"),
        NOT_LOGIN(40005, "not login"),
        INVALID_PARAM(40006, "invalid parameter"),
            PASSERROR(-1,"用户名或密码错误");
        private int code;
        private String standardMessage;

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }

    }
}
