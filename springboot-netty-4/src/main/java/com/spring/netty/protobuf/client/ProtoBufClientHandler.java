package com.spring.netty.protobuf.client;

import com.spring.netty.protobuf.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 三多
 * @Time 2019/9/17
 */
public class ProtoBufClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    /**
     * 发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Person person  =  MyDataInfo.Person.newBuilder()
                .setName("ZhangSan")
                .setAge(30)
                .setAddress("北京")
                .build();
        ctx.channel().writeAndFlush(person);
    }
}
