package com.spring.nio;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 读取数据
 * @author 三多
 * @Time 2019/9/20
 */
public class NioTest02 {
    public static void main(String[] args) throws Exception{
        //传递绝对路径 ‘/’ 开头
        URL path = NioTest02.class.getResource("/io-file/nioTest02.txt");
        String path1 = path.getPath();
        String path2 = path.getFile();
        String absolutePath = new File("io-file/nioTest02.txt").getAbsolutePath();
        //拿不到
        String property = System.getProperty("nioTest02.txt");
        //传递相对路径
        String fileName = NioTest02.class.getClassLoader().getResource("io-file/nioTest02.txt").getPath();
        FileInputStream inputStream = new FileInputStream(new File(path1));
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        channel.read(buffer);

        //读写切换
        buffer.flip();
        while(buffer.remaining() >0){
            byte b = buffer.get();
            System.out.println("Character:"+(char)b);
        }
        inputStream.close();
    }
}
