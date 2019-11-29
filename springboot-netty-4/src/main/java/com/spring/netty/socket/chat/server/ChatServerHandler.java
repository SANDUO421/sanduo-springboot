package com.spring.netty.socket.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author 三多
 * @Time 2019/9/14
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 定义channel组,存放客户端信息（保存channel对象）
     *
     * 注意： Java中多个实例的static变量会共享同一块内存区域，也就是多个对象共享一个类的同一个静态成员变量
     *
     * 两个JVM之间并不会共享数据。（static变量的线程间共享，进程间不共享）
     *
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush(channel.remoteAddress() + ",发送消息：" + msg + "\r\n");
            } else {
                ch.writeAndFlush("【自己消息】" + msg + "\r\n");
            }

        });

    }

    /**
     * 连接建立
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //注意是channelGroup 不是 ctx
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "加入\r\n");
        channelGroup.add(channel);
    }

    /**
     * 离开，会自定从channelGroup中删除对应的channel
     * 例如  手机 飞行模式或者强制关机 是不会调用此方法
     *  针对这种情况必须有心跳
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + "离开\r\n");
        System.out.println(channelGroup.size());
    }

    /**
     * 上线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
    }

    /**
     * 下线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
