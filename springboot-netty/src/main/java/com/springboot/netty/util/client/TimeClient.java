package com.springboot.netty.util.client;

import com.springboot.netty.util.server.TimeServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端开发
 * @author 三多
 * @Time 2019/9/9
 */
public class TimeClient {
    public static void main(String[] args) {
        int port  = 8088;
        if(args != null && args.length >0){
            try {
                port = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                //采用默认
            }
        }
        new TimeClient().connect("127.0.0.1",port);
    }

    private void connect(String host, int port) {
        //配置客户端NIO线程组
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        //客户端辅助启动类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new TimeClientHandler());
                    }
                });

        ChannelFuture channelFuture = null;
        try {
            //发起异步连接操作
            channelFuture = bootstrap.connect(host, port).sync();
            //等待客户端链路关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            loopGroup.shutdownGracefully();
        }


    }
}
