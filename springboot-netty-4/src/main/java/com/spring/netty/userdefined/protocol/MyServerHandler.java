package com.spring.netty.userdefined.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * 1、解析打印
 * 2、统计服务端接收到的消息内容条数
 * 3、构造写出
 *
 * @author 三多
 * @Time 2019/11/14
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    /**
     * 统计接收到的消息内容条数
     */
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        //1.读入打印
        int msgLength = msg.getLength();
        String msgContent = new String(msg.getContent(), Charset.forName("utf-8"));
        System.out.println("接收到的消息长度：" + msgLength);
        System.out.println("接收到的消息内容：" + msgContent);
        //2、统计服务端接收到的消息内容
        System.out.println("服务端接收到的消息内容条数:" + (++this.count));

        //3.构造，写出
        String responseContent = UUID.randomUUID().toString();
        int length = responseContent.getBytes(Charset.forName("utf-8")).length;
        byte[] content = responseContent.getBytes(Charset.forName("utf-8"));
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(content);
        ctx.writeAndFlush(personProtocol);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
