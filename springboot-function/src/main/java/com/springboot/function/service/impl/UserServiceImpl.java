package com.springboot.function.service.impl;

import com.springboot.function.annotation.DataFilter;
import com.springboot.function.entity.User;
import com.springboot.function.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 三多
 * @Time 2019/4/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    @DataFilter
    public User queryUserInfo(Map<String, Object> params) {
        User user = new User();
        Object username = params.getOrDefault("username", "lantian");
        Object password = params.getOrDefault("password", "lantian@123");
        user.setUsername(username != null ? String.valueOf(username):null);
        user.setPassword(password != null ? String.valueOf(password):null);
        return user;
    }
}
