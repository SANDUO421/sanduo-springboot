package com.springboot.function.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 问题：
 *      个过滤器就可以使用了，但是在我们平常的工作中一般有多个过滤器，并且要指定每个过滤器的顺序等等，
 *      那么用这种方式是没有办法 实现的，可以使用FilterRegistrationBean 来实现
 * @author 三多
 * @Time 2019/7/17
 */
@Api(description = "自定义过滤器")
@Slf4j
@RestController
@RequestMapping("/filter")
public class FilterController {

    /**
     * 自定义过滤器
     * @return
     */
    @ApiOperation(value = "测试过滤器")
    @GetMapping("/testMyFilter")
    public String testMyFilter() {
        return "Hello Filter";
    }
}
