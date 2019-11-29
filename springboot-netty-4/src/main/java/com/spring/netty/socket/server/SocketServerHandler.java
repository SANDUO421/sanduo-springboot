package com.spring.netty.socket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * 自定义handler
 * @author 三多
 * @Time 2019/9/13
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("远程访问地址："+ ctx.channel().remoteAddress()+",接收的消息为："+msg);
        ctx.writeAndFlush("from server"+ UUID.randomUUID());
    }
}
