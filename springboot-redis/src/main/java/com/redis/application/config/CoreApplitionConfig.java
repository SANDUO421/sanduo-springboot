//package com.redis.application.config;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.retry.interceptor.RetryInterceptorBuilder;
//import org.springframework.retry.interceptor.RetryOperationsInterceptor;
//
//public class CoreApplitionConfig {
//    @Bean
//    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
//    public RetryOperationsInterceptor configServerRetryInterceptor() {
//
//        return RetryInterceptorBuilder
//                .stateless()
//                .backOffOptions(1000, 1.2, 5000)
//                .maxAttempts(10)
//                .build();
//    }
//
//
//}
