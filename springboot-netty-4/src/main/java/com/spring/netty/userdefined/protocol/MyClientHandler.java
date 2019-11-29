package com.spring.netty.userdefined.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;

/**
 * 1、构造消息，给服务端端消息
 * 2、从服务端接收消息，打印
 * 3、计数接收到的数量
 *
 * @author 三多
 * @Time 2019/11/14
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //1、构造消息，给服务端端消息
        for (int i = 0; i < 10; i++) {
            String messageToSend = "send from client!";
            byte[] sendContent = messageToSend.getBytes(Charset.forName("utf-8"));
            int sendLength = sendContent.length;
            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(sendLength);
            personProtocol.setContent(sendContent);
            ctx.writeAndFlush(personProtocol);
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        //2、从服务端接收消息，打印
        @NotNull @Length(min = 0, max = 100) int msgLength = msg.getLength();
        String msgContent = new String(msg.getContent(), Charset.forName("utf-8"));
        System.out.println("接收到的消息长度：" + msgLength);
        System.out.println("接收到的消息内容：" + msgContent);

        //3、计数接收到的数量
        System.out.println("客户端接受到的消息数量：" + (++this.count));
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
