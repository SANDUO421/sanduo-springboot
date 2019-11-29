package com.spring.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/**
 * 3种缓冲区类型
 * 混合缓冲区
 * @author 三多
 * @Time 2019/11/4
 */
public class ByteBufTest03 {

    public static void main(String[] args) {
        CompositeByteBuf compositeBuffer = Unpooled.compositeBuffer();
        ByteBuf heapBuf = Unpooled.buffer(8);
        ByteBuf directBuffer = Unpooled.directBuffer(10);
        compositeBuffer.addComponents(heapBuf,directBuffer);
        Iterator<ByteBuf> iterator = compositeBuffer.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        compositeBuffer.forEach(System.out::println);
    }
}
