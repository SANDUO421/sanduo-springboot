package com.springboot.function.service;

import com.springboot.function.entity.User;

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
}
