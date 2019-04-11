package com.security.application.controller;

import com.security.application.utils.JsonResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 用户控制类
 * @author 三多
 * @Time 2019/3/22
 */
@Api(description = "User controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String listUser(){

        ArrayList<String> users = new ArrayList<>();
        users.add("aaa");
        users.add("bbb");
        users.add("ccc");
        return JsonResultUtils.returnJsonString(200,"用户列表",users);

    }
    @ApiOperation(value = "测试hello", notes = "")
    @GetMapping(value = "/hello", produces="application/json;charset=UTF-8")
    public String hello() {
        ArrayList<String> users =  new ArrayList<String>();
        users.add("hello");

        return JsonResultUtils.returnJsonString(0, "hello", users);
    }
    @ApiOperation(value = "测试world", notes = "")
    @GetMapping(value = "/world", produces="application/json;charset=UTF-8")
    public String world() {
        ArrayList<String> users =  new ArrayList<String>();
        users.add("world");
        return JsonResultUtils.returnJsonString(0, "", users);
    }
}
