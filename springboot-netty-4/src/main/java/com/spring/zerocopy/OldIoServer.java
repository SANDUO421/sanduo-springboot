package com.spring.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * IO
 * @author 三多
 * @Time 2019/10/2
 */
public class OldIoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        //循环等待
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            try {
                byte[] bytes = new byte[4096];
                while (true) {
                    int read = inputStream.read(bytes, 0, bytes.length);
                    if (read == -1) {
                        break;
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
