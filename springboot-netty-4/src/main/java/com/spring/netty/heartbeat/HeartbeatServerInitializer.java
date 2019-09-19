package com.spring.netty.heartbeat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 责任链模式 （handler）
 * @author 三多
 * @Time 2019/9/14
 */
public class HeartbeatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //空闲检测
        pipeline.addLast("idleStateHandler",new IdleStateHandler(5,7,10, TimeUnit.SECONDS));
        pipeline.addLast("heartbeatHandler",new  HeartbeatHandler());
    }
}
