package com.spring.nio;

import java.nio.ByteBuffer;

/**
 * 分割分片的方法
 *
 * @author 三多
 * @Time 2019/9/21
 */
public class NioTest07 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i=0;i<buffer.capacity() ; i++){
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);
        //操作某一个片段的buffer,底层数据就是一份
        ByteBuffer slice = buffer.slice();
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(i,b);
        }
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
