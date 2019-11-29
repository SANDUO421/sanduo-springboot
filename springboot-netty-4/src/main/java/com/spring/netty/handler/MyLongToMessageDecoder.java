package com.spring.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * long to string
 *
 * @author 三多
 * @Time 2019/11/13
 */
public class MyLongToMessageDecoder extends MessageToMessageDecoder<Long> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {
        System.out.println("MyLongToMessageDecoder decoder invoked!");
        out.add(String.valueOf(msg));
    }
}
