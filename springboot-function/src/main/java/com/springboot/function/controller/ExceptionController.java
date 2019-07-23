package com.springboot.function.controller;

import com.api.common.utils.ApiResponse;
import com.springboot.function.entity.User;
import com.springboot.function.exception.WechatLoginException;
import com.springboot.function.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @author 三多
 * @Time 2019/7/17
 */
@Api(description = "自定义异常编码")
@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {


    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试异常绑定")
    @GetMapping("/queryUserInfoThrowException")
    public ApiResponse queryUserInfo() {
        User user = userService.queryUserInfoThrowException(new HashMap<String, Object>());
        return ApiResponse.ofSuccess(user);
    }


    /**
     * 处理微信登录的异常
     */
    @ExceptionHandler(WechatLoginException.class)
    public String wechatLoginHandler(HttpServletRequest request, HttpServletResponse response, WechatLoginException e){
        log.error("微信登录异常：---Host {} invokes url {} ERROR: {}", request.getRemoteHost(), request.getRequestURL(), e.getMessage());
        response.setStatus(e.getCode());
        response.setCharacterEncoding("utf-8");
        return e.getMessage();
    }
}
