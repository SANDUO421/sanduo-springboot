package com.spring.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁
 *
 * @author 三多
 * @Time 2019/9/21
 */
public class NioTest11 {
    public static void main(String[] args) throws Exception{
        String path = NioTest10.class.getResource("/io-file/nioTest10.txt").getPath();
        //基于内存的读取
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel channel = file.getChannel();
        //文件锁
        FileLock lock = channel.lock(0, 5, true);
        System.out.println("valid: "+ lock.isValid());
        System.out.println("shared: "+ lock.isShared());
        //释放锁
        lock.release();
        file.close();
    }
}
