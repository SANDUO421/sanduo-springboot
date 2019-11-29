package com.spring.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Objects;

/**
 * 关于Buffer的Scattering（分散）和Gathering（聚合）
 * <p>
 * 数据的分门别类的存储和获取
 * <p/>
 * <p使用 telnet localhost 9999 测试<p/>
 *
 * @author 三多
 * @date 2019/9/21
 */
public class NioTest12 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));
        //定义消息格式：头+载荷+签名
        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        //接受socket连接
        SocketChannel socketChannel = channel.accept();

        while (true) {
            /*******************读取操作***************************/
            int byteRead = 0;
            //保证一次只读一个消息（完整的）
            while (byteRead < messageLength) {
                long read = socketChannel.read(buffers);
                byteRead += read;
                System.out.println("byteRead："+ byteRead);
                Arrays.asList(buffers).stream()
                        .map(buffer-> "position:"+buffer.position()+",limit:"+buffer.limit())
                        .forEach(System.out :: println);
            }
            //转换读写操作
            Arrays.asList(buffers).forEach(buffer ->{
                Objects.requireNonNull(buffers,"buffers is null");
                buffer.flip();
            });
            /********************开始写操作*********************/
            long byteWritten = 0;
            //保证一次只写一个消息（完整的）
            while(byteWritten <messageLength){
                long write = socketChannel.write(buffers);
                byteWritten += write;
            }
            Arrays.asList(buffers).forEach(buffer ->{
                //重置，或者清空
                buffer.clear();
            });
            System.out.println("byteRead:"+ byteRead+"->byteWritten:"+byteWritten+"->messageLength:"+ messageLength);
        }

    }
}
