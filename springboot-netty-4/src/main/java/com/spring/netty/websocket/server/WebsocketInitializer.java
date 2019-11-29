package com.spring.netty.websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * channel 初始化
 * 不同的handler不一样：websocket 是基于http
 *
 * @author 三多
 * @Time 2019/9/16
 */
public class WebsocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //因为websocket是基于http所以必须添加这个编解码器
        pipeline.addLast("httpServerCodec", new  HttpServerCodec());
        //批量数据写
        pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
        //分段聚合形成完整的http请求，tcp的粘包，
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(8192));
        pipeline.addLast("webSocketServerProtocolHandler",new WebSocketServerProtocolHandler("/ws"));

        //处理文本帧的handler
        pipeline.addLast("websocketHandler",new TextWebsocketFrameHandler());
    }
}
