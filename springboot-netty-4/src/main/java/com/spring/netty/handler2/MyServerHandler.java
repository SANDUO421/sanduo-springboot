package com.spring.netty.handler2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * 自定义处理器
 *
 * @author 三多
 * @Time 2019/11/11
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //目标数组长度
        byte[] dist = new byte[msg.readableBytes()];
        //元数据中读取目标数据长度
        msg.readBytes(dist);
        String message = new String(dist, Charset.forName("utf-8"));
        System.out.println("服务端接收到的消息内容：" + message);
        System.out.println("服务端介绍到的消息的数量：" + (++this.count));
        ByteBuf responseByteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString(), Charset.forName("utf-8"));
        ctx.writeAndFlush(responseByteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
