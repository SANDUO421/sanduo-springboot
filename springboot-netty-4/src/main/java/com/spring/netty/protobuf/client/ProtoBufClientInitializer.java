package com.spring.netty.protobuf.client;

import com.spring.netty.protobuf.MyDataInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author 三多
 * @Time 2019/9/17
 */
public class ProtoBufClientInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //提供了4个处理器
        pipeline.addLast("protobufVarint32FrameDecoder",new ProtobufVarint32FrameDecoder());
        //要转换的类的实例
        pipeline.addLast("protobufDecoder",new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance()));
        pipeline.addLast("protobufVarint32LengthFieldPrepender",new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        pipeline.addLast("protoBufClientHandler",new ProtoBufClientHandler());
    }
}
