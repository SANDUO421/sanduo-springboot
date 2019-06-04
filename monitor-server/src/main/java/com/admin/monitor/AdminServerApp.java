package com.admin.monitor;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 监控 的Server
 * @author 三多
 * @Time 2019/5/20
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
