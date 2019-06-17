package com.admin.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
/**
* @Description:   启动类
* @Author:         sanduo
* @CreateDate:     2019/6/6 13:01
* @Version:        1.0
*/
@EnableHystrixDashboard
@EnableTurbine
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class AdminServerApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminServerApp.class,args);
    }
}
