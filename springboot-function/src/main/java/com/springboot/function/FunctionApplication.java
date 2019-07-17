package com.springboot.function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * 过滤器：@ServletComponentScan
 * @author 三多
 * @Time 2019/4/28
 */
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
//@ServletComponentScan
@SpringBootApplication
public class FunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }
}
