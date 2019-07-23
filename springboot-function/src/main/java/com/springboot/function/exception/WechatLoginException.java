package com.springboot.function.exception;

import com.springboot.function.utils.HttpStatusEnum;

/**
 * @author 三多
 * @Time 2019/7/17
 */
public class WechatLoginException extends RuntimeException {
    /**
     * 异常状态码
     */
    private int code;

    /**
     * 模拟微信登录
     * @param httpStatusEnum
     */
    public WechatLoginException(HttpStatusEnum httpStatusEnum) {
        super(httpStatusEnum.getDescription());
        code = httpStatusEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
