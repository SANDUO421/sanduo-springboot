package com.spring.netty.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Server端
 * @author 三多
 * @Time 2019/9/13
 */
public class SocketServer {

    public static void main(String[] args) throws Exception{
        /****事件循环组，死循环***/
        //获取客户端链接，不做事情，将链接给workerGroup（转发），1 个线程,设置成1就ok
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //获取链接，处理业务代码和编解码
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //handler 处理 bossGroup；childHandler 处理 workerGroup
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new SocketServerInitializer());
            //启动，Reactor模式 创建 Acceptor对象
            ChannelFuture future = bootstrap.bind(9999).sync();
            future.channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
    }
}
