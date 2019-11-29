package com.springboot.netty.util.server;

import com.springboot.netty.util.client.TimeClient;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty 时间服务器 Server端
 *
 * @author 三多
 * @version 1.0
 * @date 2019/9/9
 */
public class TimeServer {
    /**
     * 绑定
     *
     * @param port
     */
    public void bind(int port) {
        //配置服务端NIO线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            //绑定端口，同步等待成功
            ChannelFuture f = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅的释放资源
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        int port  = 8088;
        if(args != null && args.length >0){
            try {
                port = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                //采用默认
            }
        }
        new TimeServer().bind(port);
    }


}
