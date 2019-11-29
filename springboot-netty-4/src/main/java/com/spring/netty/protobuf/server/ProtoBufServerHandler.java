package com.spring.netty.protobuf.server;

import com.spring.netty.protobuf.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**MyDataInfo.Person  编解码的类型
 * @author 三多
 * @Time 2019/9/17
 */
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person person) throws Exception {
        System.out.println(person.getName()+"-"+person.getAge()+"-"+person.getAddress());

    }

}
