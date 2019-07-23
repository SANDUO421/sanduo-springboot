package com.springboot.function.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * @author 三多
 * @Time 2019/7/17
 */
@Api(description = "自定义拦截器")
@Slf4j
@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    /**
     * 自定义拦截器
     * @return
     */
    @ApiOperation(value = "测试拦截器")
    @GetMapping("/testInterceptor")
    public ModelAndView testInterceptor() {
        System.out.println("进入controller");
        ModelAndView mv = new ModelAndView();

        mv.setViewName("aaa");
        System.out.println("即将返回modelandview");
        return mv;
    }

}
