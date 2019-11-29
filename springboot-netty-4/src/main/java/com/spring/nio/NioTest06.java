package com.spring.nio;

import java.nio.ByteBuffer;

/**
 * 类型化的buffer
 * 数据类型
 *
 * @author 三多
 * @Time 2019/9/21
 */
public class NioTest06 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        //buffer.put("nnn".getBytes()); //添加就会报错
        buffer.putChar('张');
        buffer.putShort((short) 2);
        buffer.putInt(12);
        buffer.putFloat(12.11F);
        buffer.putDouble(12.1);
        buffer.putLong(12000L);

        buffer.flip();
        //顺序必须和存储的顺序一致，否则解析就出错
        //System.out.println(buffer.get());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getFloat());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getLong());


    }
}
