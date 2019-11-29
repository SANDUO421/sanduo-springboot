package com.spring.netty.userdefined.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 解码器-解析协议
 * 将数据读取，封装。
 *
 * @author 三多
 * @Time 2019/11/14
 */
public class MyPersonDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyPersonDecoder decoder invoked!");
        //获取消息长度
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);
        PersonProtocol protocol = new PersonProtocol();
        protocol.setLength(length);
        protocol.setContent(content);
        //解析
        out.add(protocol);

    }
}
