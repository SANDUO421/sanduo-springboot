package com.monitor.springboot.servicemonitor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 三多
 * @Time 2019/5/20
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @GetMapping(value = "/test")
    public String helloConsumer() {
        return "ribbon - test";
    }
}
