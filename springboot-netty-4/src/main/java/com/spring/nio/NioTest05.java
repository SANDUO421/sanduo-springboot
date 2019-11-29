package com.spring.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 获取文件的输入，写到另外一个文件中
 *
 * @author 三多
 * @Time 2019/9/21
 */
public class NioTest05 {
    public static void main(String[] args) throws Exception {
        String in = NioTest05.class.getResource("/io-file/input.txt").getPath();
        String out = NioTest05.class.getResource("/io-file/output.txt").getPath();
        FileInputStream inputStream = new FileInputStream(new File(in));
        FileOutputStream outputStream = new FileOutputStream(new File(out));

        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            //初始化Buffer,如果将该行注释掉，当读取的自己大于容器的1024，竟会报异常
            buffer.clear();
            int read = inChannel.read(buffer);
            System.out.println("read:" + read);
            if (read == -1) {
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }
}
