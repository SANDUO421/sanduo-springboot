package com.spring.netty.http.server;

import com.spring.netty.http.init.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Http Server
 *
 * @author 三多
 * @Time 2019/9/11
 */
public class HttpServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //启动服务器配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            //定义管道初始化,childHandler 请求处理器
            bootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
            //sync()保证绑定的完成，这是必须调用的
            ChannelFuture future = bootstrap.bind(9999).sync();
            future.channel().closeFuture().sync();

        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
