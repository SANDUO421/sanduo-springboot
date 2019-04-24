package com.redis.application.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 三多
 * @Time 2019/4/24
 */
@Configuration
@Data
public class AppConfig {


    private String dfsUrl = "192.168.10.21:22122";


}
