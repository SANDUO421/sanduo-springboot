package com.spring.netty.userdefined.protocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 通道初始化器
 *
 * @author 三多
 * @Time 2019/11/14
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("myPersonDecoder", new MyPersonDecoder());
        pipeline.addLast("myPersonEncoder", new MyPersonEncoder());
        pipeline.addLast("myServerHandler", new MyServerHandler());

    }
}
