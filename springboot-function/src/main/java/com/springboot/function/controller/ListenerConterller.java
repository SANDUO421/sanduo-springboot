package com.springboot.function.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 监听器
 * @author 三多
 * @Time 2019/7/17
 */
@Api(description = "自定义监听器")
@Slf4j
@RestController
@RequestMapping("/listener")
public class ListenerConterller {

    /**
     * 自定义监听器
     * @param req
     * @return
     */
    @ApiOperation(value = "测试监听器")
    @GetMapping("/testListenerLogin")
    public String testListenerLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("age","12");
        //30秒
        session.setMaxInactiveInterval(600);
        System.out.println("当前在线人数"+ session.getId()+"："
                + session.getServletContext().getAttribute("count"));
        return "Hello testListenerLogin";
    }

}
