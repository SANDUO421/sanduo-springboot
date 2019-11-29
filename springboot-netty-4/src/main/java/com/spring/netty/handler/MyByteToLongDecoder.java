package com.spring.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义编码器
 *
 * @author 三多
 * @Time 2019/11/11
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode invoked!");
        System.out.println("可读的字节数" + in.readableBytes());
        //整形8个字节
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
