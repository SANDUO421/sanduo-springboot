package com.spring.nio;

import java.nio.ByteBuffer;

/**
 * 只读Buffer
 *
 * @author 三多
 * @Time 2019/9/21
 */
public class NioTest08 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.getClass());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(((byte) i));
        }
        //可以将一个读写buffer转换成只读buffer，但是不能将一个只读buffer转换成读写buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
    }
}
