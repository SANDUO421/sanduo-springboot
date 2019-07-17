package com.springboot.function.service;

import com.springboot.function.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 三多
 * @Time 2019/4/29
 */
public interface UserService {
    /**
     * 获取用户信息
     * @param params
     * @return
     */
    User queryUserInfo(Map<String, Object> params);

    /**
     * 抛出自定义异常
     * @param params
     * @return
     */
    User queryUserInfoThrowException(Map<String, Object> params);
}
