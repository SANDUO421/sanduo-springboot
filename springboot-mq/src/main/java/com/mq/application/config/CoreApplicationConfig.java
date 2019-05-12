package com.mq.application.config;

import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
* @Description:    解决bug：A component required a bean named 'configServerRetryInterceptor' that could not be found.
* @Author:         sanduo
* @CreateDate:     2019/5/9 10:47
* @Version:        1.0
*/
public class CoreApplicationConfig {
    @Bean
    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
    public RetryOperationsInterceptor configServerRetryInterceptor() {

        return RetryInterceptorBuilder
                .stateless()
                .backOffOptions(1000, 1.2, 5000)
                .maxAttempts(10)
                .build();
    }
}
