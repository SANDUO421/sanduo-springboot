package com.spring.netty.userdefined.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 *
 * @author 三多
 * @Time 2019/11/14
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {
    /**
     * 编码
     *
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder encoder invoked !");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
