package com.spring.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 位于堆外内存
 * @author 三多
 * @Time 2019/9/21
 */
public class NioTest10 {
    public static void main(String[] args) throws Exception{
        String path = NioTest10.class.getResource("/io-file/nioTest10.txt").getPath();
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel channel = file.getChannel();
        //直接基于内存
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        //
        map.put(0, ((byte) 'a'));
        map.put(3, ((byte) 'm'));
        file.close();

    }
}
