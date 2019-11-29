package com.spring.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author 三多
 * @Time 2019/10/2
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        ServerSocket socket = channel.socket();
        socket.setReuseAddress(true);
        socket.bind(new InetSocketAddress(9999));
        ByteBuffer buffer =ByteBuffer.allocate(4096);
        while(true){
            SocketChannel socketChannel = channel.accept();
            socketChannel.configureBlocking(true);
            int readCount = 0;
            while (readCount != -1){
                try {
                    readCount = socketChannel.read(buffer);
                }catch(Exception ex){
                    //TODO 客户端关闭会导致无线进入死循环
                   ex.printStackTrace();
                }
                //一定要充值buffer
                buffer.rewind();
            }

        }


    }
}
