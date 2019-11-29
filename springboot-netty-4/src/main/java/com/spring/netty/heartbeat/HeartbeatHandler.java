package com.spring.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author 三多
 * @Time 2019/9/14
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
       if(evt instanceof IdleStateEvent){

           IdleStateEvent stateEvent = ((IdleStateEvent) evt);
           String eventType = "";
           switch (stateEvent.state()){
               case READER_IDLE:
                   eventType ="读空闲";
                   break;
               case WRITER_IDLE:
                   eventType = "写空闲";
                   break;
               case ALL_IDLE:
                   eventType="读写空闲";
                   break;
           }
           System.out.println(ctx.channel().remoteAddress() +",超时事件:"+eventType);
           ctx.channel().close();

       }
    }

}
