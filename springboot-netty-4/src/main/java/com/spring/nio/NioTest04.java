package com.spring.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * 理解 capacity, limit, and position (capacity>=limit>=position>=mark>=0 )
 *
 * @author 三多
 * @Time 2019/9/19
 */
public class NioTest04 {
    public static void main(String[] args) throws Exception {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("capacity =" + buffer.capacity());
        for (int i = 0; i < 5; i++) {
            int random = SecureRandom.getInstanceStrong().nextInt(10);
            buffer.put(random);
        }
        System.out.println("before flip limit =" + buffer.limit());
        //读写切换
        buffer.flip();
        System.out.println("after flip limit =" + buffer.limit());
        while (buffer.hasRemaining()) {
            System.out.println("position =" + buffer.position());
            System.out.println("limit =" + buffer.limit());
            System.out.println("capacity =" + buffer.capacity());
            System.out.println(buffer.get());
        }
    }
}
