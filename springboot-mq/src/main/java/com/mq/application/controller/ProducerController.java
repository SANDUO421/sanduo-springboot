package com.mq.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Encoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 生产者
 * @author 三多
 * @Time 2019/5/9
 */

@Api(description = "发送消息")
@RestController
@RequestMapping("/send")
public class ProducerController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 给hello队列发送消息
     */
    @ApiOperation(value = "发送消息send")
    @PutMapping("/send")
    public void send() {
        for (int i =0; i< 100; i++) {
            String msg = "hello, 序号: " + i;
            System.out.println("Producer, " + msg);
            rabbitTemplate.convertAndSend("queue-test", msg);
        }
    }
    /**
     * 给hello队列发送消息:map
     */
    @ApiOperation(value = "发送消息send Map")
    @PutMapping("/sendMap")
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
