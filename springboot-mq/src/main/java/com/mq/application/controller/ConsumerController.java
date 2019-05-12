package com.mq.application.controller;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EncodingUtils;
import org.omg.IOP.Encoding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.AudioFormat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 消费者
 * @author 三多
 * @Time 2019/5/9
 */
@Slf4j
@RestController
public class ConsumerController {
   // private Logger log = LoggerFactory.getLogger(Comsumer.class);

    @RabbitListener(queues = "queue-test")
    public void process(Message message, Channel channel) throws IOException {
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        //log.info("receive: " + new String(message.getBody()));
        byte[] body = message.getBody();
        ByteArrayInputStream byteInt=new ByteArrayInputStream(body);
        ObjectInputStream objInt=new ObjectInputStream(byteInt);
        Map<String, Object> result = null;
        try {
            result = (Map<String, Object>) objInt.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.info("receive: " + result);
    }
}
