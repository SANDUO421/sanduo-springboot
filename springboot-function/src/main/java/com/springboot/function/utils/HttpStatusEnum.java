package com.springboot.function.utils;

import lombok.Data;

/**
 * @author 三多
 * @Time 2019/7/17
 */

public enum HttpStatusEnum {
    StudentNotFound("未找到该学生", 601),
    StudentIsForzen("该学生的状态已冻结", 602),
    StudentISBinded("该学号已被其他微信帐号绑定，请联系老师或者管理员解除绑定", 603),
    WecahtIsBinded("该微信帐号已绑定学号，请解除绑定后重新登录", 604);

    private String description;
    private int code;

    HttpStatusEnum(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
