package com.springboot.function.config;

import com.springboot.function.listener.CountListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监听器
 *
 * @author 三多
 * @Time 2019/7/17
 */
@Configuration
public class ListenerConfig {

    @Bean
    public ServletListenerRegistrationBean<CountListener> countListenerServletRegistrationBean() {
        return new ServletListenerRegistrationBean<CountListener>(
                new CountListener());
    }
}
