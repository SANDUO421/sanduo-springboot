package com.spring.netty.protobuf2.client;

import com.spring.netty.protobuf2.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author 三多
 * @Time 2019/9/17
 */
public class ProtoBufClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    /**
     * 发送数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomNum = new Random().nextInt(3);
        MyDataInfo.MyMessage message = null;
        if (0 == randomNum) {
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder()
                            .setName("ZhangSan")
                            .setAge(30)
                            .setAddress("北京").build()
                    )
                    .build();
        } else if (1 == randomNum) {
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("阿黄")
                            .setAge(4)
                            .build()
                    )
                    .build();

        } else {
            message = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setName("小花")
                            .setAge(5)
                            .build()
                    )
                    .build();

        }

        ctx.channel().writeAndFlush(message);
    }
}
