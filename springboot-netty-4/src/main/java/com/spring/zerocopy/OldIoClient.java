package com.spring.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author 三多
 * @Time 2019/10/2
 */
public class OldIoClient {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost",9999);
        String file = OldIoClient.class.getResource("/io-file/paydemo.txt").getPath();
        InputStream inputStream = new FileInputStream(file);
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long readCount;
        long total =0;
        long start = System.currentTimeMillis();
        while((readCount = inputStream.read(buffer)) >=0){
            total += readCount;
            outputStream.write(buffer);
        }
        long end = System.currentTimeMillis();
        System.out.println("发送的总字节数："+ total + ",耗时："+(end-start));

        outputStream.close();
        inputStream.close();
        socket.close();

    }
}
