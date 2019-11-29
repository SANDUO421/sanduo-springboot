package com.spring.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * Nio 异步编程
 * 一个线程处理多个客户端请求
 *
 * @author 三多
 * @Time 2019/9/21
 */
public class NioTest13 {
    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;
        //多路选择器，（通过SelectionKey注册）
        Selector selector = Selector.open();
        //不同操作系统使用的实现类也不一样 WindowsSelectorProvider
        System.out.println(SelectorProvider.provider().getClass());
        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel channel = ServerSocketChannel.open();
            //不阻塞
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(ports[i]));
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口：" + ports[i]);
        }
        while (true) {
            //获取key的数量
            int num = selector.select();
            System.out.println("numbers:" + num);
            Set<SelectionKey> keySet = selector.selectedKeys();
            System.out.println("selectionKey:" + keySet);
            for (SelectionKey key : keySet) {
                //测试socket是否接受新的连接
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = ((ServerSocketChannel) key.channel());
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    //当前事件用完了，一定要删除掉
                    keySet.remove(key);
                    System.out.println("获取客户端连接：" + socketChannel);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = ((SocketChannel) key.channel());
                    int byteRead = 0;
                    ByteBuffer buffer = ByteBuffer.allocate(512);
                    while (true) {
                        buffer.clear();
                        int read = socketChannel.read(buffer);
                        //注意一定是<=，否则不会访问成功
                        if (read <= 0) {
                            break;
                        }
                        buffer.flip();
                        //写
                        socketChannel.write(buffer);
                        byteRead += read;
                    }
                    System.out.println("读取："+ byteRead+"来自于："+socketChannel);
                    //不能忘记
                    keySet.remove(key);
                }
            }
        }
    }
}
