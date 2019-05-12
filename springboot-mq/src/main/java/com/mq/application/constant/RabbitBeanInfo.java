package com.mq.application.constant;

/**
 * rabbit 公共配置
 * @author 三多
 * @Time 2019/5/9
 */
public class RabbitBeanInfo {
    // queue 配置
    public static final String QUEUE_NAME = "spring.boot.bean.queue";
    public static final String QUEUE_DURABLE = "true";

    // exchange 配置
    public static final String EXCHANGE_NAME = "spring.boot.bean.exchange";
    public static final String EXCHANGE_TYPE = "direct";

    // routing key
    public static final String ROUTING_KEY = "springboot.bean";

}
