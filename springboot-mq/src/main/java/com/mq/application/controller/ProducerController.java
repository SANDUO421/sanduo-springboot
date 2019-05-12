package com.mq.application.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Encoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 生产者
 * @author 三多
 * @Time 2019/5/9
 */
@RestController
public class ProducerController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 给hello队列发送消息
     */
    public void send() {
        for (int i =0; i< 100; i++) {
            String msg = "hello, 序号: " + i;
            System.out.println("Producer, " + msg);
            rabbitTemplate.convertAndSend("queue-test", msg);
        }
    }
    /**
     * 给hello队列发送消息:sendId
     */
    public void sendId() {
        for (int i =0; i< 100; i++) {
            Map map  = new HashMap<String,Integer>();
            map.put("jobId",i);
            System.out.println("Produce的Id: " + map);
            rabbitTemplate.setEncoding("utf-8");
            rabbitTemplate.convertAndSend("queue-test", map);
        }
    }
}
