package com.tourism.bigdatatourism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
/**
* @Description:    配置
* @Author:         sanduo
* @CreateDate:     2019/4/3 14:41
* @Version:        1.0
*/
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class BigdataTourismApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigdataTourismApplication.class, args);
    }

}
