package com.spring.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author 三多
 * @Time 2019/9/14
 */
public class HeartbeatServer {
    public static void main(String[] args) throws Exception{
        //获取链接，不做事情，将链接给workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //获取链接，处理业务代码和编解码
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //handler 处理 bossGroup；childHandler 处理 workerGroup
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    //添加日志handler，针对bossGroup 而言
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //针对workerGroup 而言
                    .childHandler(new HeartbeatServerInitializer());
            ChannelFuture future = bootstrap.bind(9999).sync();
            future.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
    }
}
