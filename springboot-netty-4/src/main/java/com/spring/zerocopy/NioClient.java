package com.spring.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author 三多
 * @Time 2019/10/2
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost",9999));
        //阻塞，当为false 时，文件太大会缓冲阻塞
        channel.configureBlocking(true);
        //paydemo.txt 就是大文件，打不开原文件名paydemo.zip
        String file = NioClient.class.getResource("/io-file/paydemo.txt").getPath();
        FileChannel fileChannel = new FileInputStream(file).getChannel();
        long start = System.currentTimeMillis();
        long count = fileChannel.transferTo(0, fileChannel.size(), channel);
        long end = System.currentTimeMillis();
        System.out.println("发送的总字节数："+ count + ",耗时："+(end-start));

        fileChannel.close();
    }
}
