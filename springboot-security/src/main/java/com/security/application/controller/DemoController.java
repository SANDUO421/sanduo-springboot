package com.security.application.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;

/**
 * demoController
 * @author 三多
 * @Time 2019/3/22
 */
@Api(description = "demo")
@RestController
public class DemoController {

    @GetMapping(value = "/hello")
    public Map<String,String> hello() {
        Map<String,String> map = new HashMap<>();
        map.put("content","hello Security");
        return map;
    }
}
