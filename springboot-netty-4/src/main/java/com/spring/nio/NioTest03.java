package com.spring.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 写出数据
 *
 * @author 三多
 * @Time 2019/9/20
 */
public class NioTest03 {
    public static void main(String[] args) throws Exception {
        //写到target/classes中去了
        String file = NioTest03.class.getResource("/io-file/nioTest03.txt").getFile();
        String filea = NioTest03.class.getResource("/io-file/nioTest03.txt").getPath();
        FileOutputStream outputStream = new FileOutputStream(new File(file));
        //FileOutputStream outputStream = new FileOutputStream("io-file/nioTest03.txt");
        ByteBuffer buffer = ByteBuffer.allocate(512);
        byte[] message = "hello world welcome,nice!".getBytes();
        FileChannel channel = outputStream.getChannel();

        for (int i=0 ; i <message.length ;++i){
            buffer.put(message[i]);
        }
        buffer.flip();
        channel.write(buffer);
        outputStream.close();

    }


}
