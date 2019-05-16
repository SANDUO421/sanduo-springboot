package com.monitor.springboot.servicemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 访问：http://localhost:8090/monitoring/actuator
* @Description:    微服务监控
* @Author:         sanduo
* @CreateDate:     2019/5/15 15:37
* @Version:        1.0
*/
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMonitorApplication.class, args);
    }

}
