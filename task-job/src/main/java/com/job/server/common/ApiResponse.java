package com.job.server.common;

/**
 * @ProjectName: microservice-house
 * @Auther: 库琦
 * @Date: 2018/12/25
 * @Description: 定制Api响应类(Result)
 */
public class ApiResponse<T> {
    private int code; // 状态码
    private String msg; // 消息
    private T data; // 返回的数据
    public ApiResponse() {

    }
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 定义常用静态方法
     */
    public static ApiResponse ofSuccess(Object data) {
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    public static ApiResponse ofSuccess() {
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), null);
    }

    public static ApiResponse ofMessage(int code, String message) {
        return new ApiResponse(code, message, null);
    }

    public static ApiResponse ofStatus(Status status) {
        return new ApiResponse(status.getCode(), status.getStandardMessage(), null);
    }

    /**
     * 定义状态枚举
     */
    public enum Status {
        SUCCESS(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "page not found"),
        INTERNAL_SERVER_ERROR(500, "server internal error"),
        NOT_LOGIN(40005, "not login"),
        INVALID_PARAM(40006, "invalid parameter");

        private int code;
        private String standardMessage;

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }

        public void setStandardMessage(String standardMessage) {
            this.standardMessage = standardMessage;
        }
    }
}
