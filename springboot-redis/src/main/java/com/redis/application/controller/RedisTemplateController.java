package com.redis.application.controller;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RedisTemplate demo
 * @author 三多
 * @Time 2019/4/11
 */
@RestController
@EnableCaching
public class RedisTemplateController {

    //@Value("${spring.redis.host}")
    //private String host;
    //@Value("${spring.redis.port}")
    //private int port;
    //@Value("${spring.redis.password}")
    //private  String password;
    //@Value("${my.config}")
    //private  String url;

    @GetMapping("/demo")
    public  String demo(){

        //System.out.println("host:"+host);
        //System.out.println("port:"+port);
        //System.out.println("password:"+password);
        //System.out.println("url:"+url);
        return "demo";
    }


}
