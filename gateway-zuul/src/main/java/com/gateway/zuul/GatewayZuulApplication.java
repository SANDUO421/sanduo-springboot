package com.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
* @Description:    API网关
* @Author:         sanduo
* @CreateDate:     2019/4/26 17:32
* @Version:        1.0
*/
@RefreshScope
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulApplication.class, args);
    }

}
