package com.redis.application.utils;

import com.google.code.kaptcha.Producer;
import com.redis.application.RedisApplication;
import com.redis.application.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 需求：
 * 1、生成验证码存储到redis中。
 * 2、从redis中读取验证码，确认验证码的正确性
 *
 * @author 三多
 * @Time 2019/8/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
@Slf4j
public class GenerateKaptcha {

    @Autowired(required = false)
    private Producer producer;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 验证码key
     */
    private String kaptchaKey = "cokeKey" + UUID.randomUUID().toString().trim();


    /**
     * 1.生成验证码进入redis
     */
    @Test
    public void setKaptchaInRedis() throws IOException {
        //1、生成图片
        String text = producer.createText();

        BufferedImage image = producer.createImage(text);

        //2、写出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        byte[] bytes = outputStream.toByteArray();
        byte2image(bytes, "D:\\1.jpg");
        System.out.println(outputStream);
        //3、将图片存储到Redis
        //修改默认的java序列化机制
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //缓存中的5秒过期
        redisTemplate.opsForValue().set(kaptchaKey, text, 10, TimeUnit.MINUTES);


    }

    /**
     * 2.获取验证码从redis 并比较正确性
     */
    @Test
    public void getKaptchaformRedis() {
        String codekey = "pyan8";
        //修改默认的java序列化机制
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        Object value = redisTemplate.opsForValue().get("cokeKeyd2e83a8e-b679-458e-b786-a73af6911dd3");
        if (value == null) {
            log.info("验证码失效");
            throw new BusinessException("验证码失效","1112");
        }
        if (StringUtils.isNotBlank(codekey) && codekey.trim().equals(value)) {
            log.info("验证码校验成功");
        } else {
            log.info("验证码校验失败");
            throw new BusinessException("验证码校验失败","1113");
        }

    }


    /**
     * byte数组到图片到硬盘上
     */
    public void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals(""))
            return;
        //判断输入的byte是否为空
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            //打开输入流
            imageOutput.write(data, 0, data.length);
            //将byte写入硬盘
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

}
