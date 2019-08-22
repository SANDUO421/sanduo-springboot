package com.redis.application.utils;

import lombok.Data;

/**
 * 统一异常处理
 * @author 三多
 * @Time 2019/8/22
 */
@Data
public class CommonResult<T> {
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 业务状态
     */
    private String status;



    /**
     * 2.第二步 定义统一异常处理方法：封装统一返回格式
     * @param msg
     * @param code
     * @param <T>
     * @return
     */
    public static  <T> CommonResult<T>  errorResult(String msg,String code){
        CommonResult<T> result = new CommonResult<>();
        result.setErrorCode(code);
        result.setErrorMsg(msg);
        result.setStatus("-1");
        return result;
    }
}
