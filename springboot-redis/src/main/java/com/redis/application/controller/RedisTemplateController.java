package com.redis.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RedisTemplate demo
 *
 * 注解缓存的使用
 * @Cacheable：在方法执行前Spring先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；
 *              没有则调用方法并将方法返回值放进缓存。
 * @CachePut：将方法的返回值放到缓存中。
 * @CacheEvict：删除缓存中的数据。
 *
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
