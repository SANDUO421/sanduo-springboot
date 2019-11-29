package com.spring.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 初始化处理器链
 *
 * @author 三多
 * @Time 2019/11/11
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //自定义解码器
        pipeline.addLast(new MyByteToLongDecoder2());
        pipeline.addLast(new MyLongToMessageDecoder());
        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast("myServerHandler",new MyServerHandler());

    }
}
