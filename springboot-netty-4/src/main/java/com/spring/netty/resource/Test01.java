package com.spring.netty.resource;

import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @author 三多
 * @Time 2019/10/4
 */
public class Test01 {
    /**
     * coreNum {@link MultithreadEventLoopGroup#DEFAULT_EVENT_LOOP_THREADS}
      */
    public static void main(String[] args) {

        int coreNum = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(coreNum);

    }
}
