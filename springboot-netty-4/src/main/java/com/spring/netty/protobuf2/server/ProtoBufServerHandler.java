package com.spring.netty.protobuf2.server;

import com.spring.netty.protobuf2.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**MyDataInfo.Person  编解码的类型
 * @author 三多
 * @Time 2019/9/17
 */
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if(dataType == MyDataInfo.MyMessage.DataType.PersonType){
            MyDataInfo.Person person = msg.getPerson();

            System.out.println(person.getName()+"-"+person.getAge()+"-"+person.getAddress());
        }else if(dataType == MyDataInfo.MyMessage.DataType.DogType){
            MyDataInfo.Dog dog = msg.getDog();

            System.out.println(dog.getName()+"-"+dog.getAge());
        }else {
            MyDataInfo.Cat cat = msg.getCat();

            System.out.println(cat.getName()+"-"+cat.getAge());
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
