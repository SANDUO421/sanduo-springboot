package com.spring.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * 生成是十个随机数
 *
 * @author 三多
 * @Time 2019/9/19
 */
public class NioTest01 {
    public static void main(String[] args) throws Exception {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int random = SecureRandom.getInstanceStrong().nextInt(10);
            buffer.put(random);
        }
        //读写切换
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
