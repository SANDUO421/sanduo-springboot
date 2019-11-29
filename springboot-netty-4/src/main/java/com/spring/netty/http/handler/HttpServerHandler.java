package com.spring.netty.http.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URL;


/**
 * 自定义handler
 * @author 三多
 * @Time 2019/9/11
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("类："+ctx.getClass());
        System.out.println("远程地址："+ctx.channel().remoteAddress());
        Thread.sleep(1000);
        if(msg instanceof HttpRequest){
            HttpRequest req  = (HttpRequest)msg;
            HttpMethod method = req.method();
            System.out.println("请求方式："+HttpMethod.GET);
            System.out.println("methodName："+ method.name());
            String uri = req.uri();
            //不加就会获取不到对应的url
            uri = "http://localhost:9999";
            URL url = new URL(uri);
            if("/favicon.ico".equals(url.getPath())){
                System.out.println("请求地址：/favicon.ico");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("HELLO World", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
            ctx.channel().close();

        }

    }
}
