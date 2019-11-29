package com.spring.netty.handler2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @author 三多
 * @Time 2019/11/11
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ByteBuf sentFromClient = Unpooled.copiedBuffer("sent from client", Charset.forName("utf-8"));
            ctx.writeAndFlush(sentFromClient);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] dist = new byte[msg.readableBytes()];
        msg.readBytes(dist);
        String message = new String(dist, Charset.forName("utf-8"));
        System.out.println("客户端收到的消息内容：" + message);
        System.out.println("客户端收到的消息数量：" + (++this.count));

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }
}
