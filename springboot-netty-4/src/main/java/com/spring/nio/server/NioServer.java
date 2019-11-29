package com.spring.nio.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author 三多
 * @Time 2019/9/23
 */
public class NioServer {
    private static Map<String, SocketChannel> clientMap = new HashMap<>(16);

    public static void main(String[] args) throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        //配置非阻塞
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(9999));

        //创建Selector对象（一个Selector对象可以注册多个channel）
        Selector selector = Selector.open();
        //接收连接，ServerSocketChannel->OP_ACCEPT 是一个来连接事件,关注连接对象
        channel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                //阻塞selector，返回的关注的事件的数量
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {
                            //注册了ServerSocketChannel -》accept
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            //接收一个serverSocket连接
                            client = server.accept();
                            //配置非阻塞
                            client.configureBlocking(false);
                            //SocketChannel 对应的事件OP_READ；注册到selector上
                            client.register(selector, SelectionKey.OP_READ);
                            String key = "[" + UUID.randomUUID().toString() + "]";
                            clientMap.put(key, client);
                        } else if (selectionKey.isReadable()) {
                            //因为只是对SocketChannel进行注册了OP_READ事件，所以一定如下
                            client = (SocketChannel) selectionKey.channel();
                            //可以读也可以写
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            //结尾0或者-1
                            int count = client.read(buffer);

                            if (count > 0) {
                                //写
                                buffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receiveMessage = String.valueOf(charset.decode(buffer).array());
                                System.out.println(client + ":" + receiveMessage);

                                //读
                                String sendKey = null;
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        sendKey = entry.getKey();
                                    }
                                }
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();
                                    ByteBuffer writeBuf = ByteBuffer.allocate(1024);
                                    writeBuf.put((sendKey + ":" + receiveMessage).getBytes(Charset.defaultCharset()));
                                    writeBuf.flip();
                                    value.write(writeBuf);
                                }

                            }

                        }
                        //必须remove，否则会进入死循环
                        selectionKeys.remove(selectionKey);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                });

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        }

    }

}
