package com.spring.netty.protobuf2.server;

import com.spring.netty.protobuf2.MyDataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author 三多
 * @Time 2019/9/17
 */
public class ProtoBufServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //提供了4个处理器
        pipeline.addLast("protobufVarint32FrameDecoder",new ProtobufVarint32FrameDecoder());
        //要转换的类的实例，如何动态改变消息类型 MyDataInfo.Person.getDefaultInstance()
        pipeline.addLast("protobufDecoder",new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
        pipeline.addLast("protobufVarint32LengthFieldPrepender",new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new  ProtobufEncoder());
        pipeline.addLast("protoBufServerHandler",new ProtoBufServerHandler());
    }
}
