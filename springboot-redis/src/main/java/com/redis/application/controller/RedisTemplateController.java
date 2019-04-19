package com.redis.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RedisTemplate demo
 * @author 三多
 * @Time 2019/4/11
 */
@Api(description = "redis 测试controller")
@RestController
@EnableCaching
public class RedisTemplateController {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private  String password;
    @ApiOperation(value = "测试demo接口")
    @GetMapping("/demo")
    public  String demo(){

        System.out.println("host:"+host);
        System.out.println("port:"+port);
        System.out.println("password:"+password);

        return "demo";
    }


}
