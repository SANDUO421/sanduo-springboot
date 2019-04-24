package com.auth2.permit;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringCloudApplication
public class Auth2CenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(Auth2CenterApplication.class,args);
    }
}
