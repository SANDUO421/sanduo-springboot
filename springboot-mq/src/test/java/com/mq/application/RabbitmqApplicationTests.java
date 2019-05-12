package com.mq.application;

import com.mq.application.constant.RabbitBeanInfo;
import com.mq.application.constant.RabbitInfo;
import com.mq.application.controller.ProducerController;
import com.mq.application.controller.RabbitmqBeanConsumer;
import com.mq.application.controller.RabbitmqProducer;
import com.mq.application.entity.Programmer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 三多
 * @Time 2019/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqApplication.class)
public class RabbitmqApplicationTests {
    @Autowired
    private ProducerController producerController;

    @Autowired
    private RabbitmqProducer producer;

    /**
     * 用string接收
     */
    @Test
    public void contextLoads() {
        producerController.send();
    }

    /**
     * 用map接收
     */
    @Test
    public void sendId() {
        producerController.sendId();
    }

    /***
     * 发送消息体为简单数据类型的消息
     */
    @Test
    public void send() {
        Map<String, Object> heads = new HashMap<>();
        heads.put("msgInfo", "自定义消息头信息");
        // 模拟生成消息ID,在实际中应该是全局唯一的 消息不可达时候可以在setConfirmCallback回调中取得，可以进行对应的重发或错误处理
        String id = String.valueOf(Math.round(Math.random() * 10000));
        producer.sendSimpleMessage(heads, "hello Spring", id, RabbitInfo.EXCHANGE_NAME, "springboot.simple.abc");
    }


    /***
     * 发送消息体为bean的消息
     */
    @Test
    public void sendBean() {
        String id = String.valueOf(Math.round(Math.random() * 10000));
        Programmer programmer = new Programmer("xiaoMing", 12,LocalDate.now());
        producer.sendSimpleMessage(null, programmer, id, RabbitBeanInfo.EXCHANGE_NAME, RabbitBeanInfo.ROUTING_KEY);
    }

}
