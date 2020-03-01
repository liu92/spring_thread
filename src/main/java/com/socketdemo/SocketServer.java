package com.socketdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket 回顾
 * @ClassName: SocketServer
 * @Description:
 * @Author: lin
 * @Date: 2019/10/16 13:42
 * History:
 * @<version> 1.0
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        // 监听指定的端口
        int port = 55533;
        ServerSocket serverSocket = new ServerSocket(port);
        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        // 服务器调用ServerSocket类的accept()方法。此方法等待，直到客户端连接到给定端口上的服务器。
        Socket socket = serverSocket.accept();
        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len,"UTF-8"));
        }
        System.out.println("get message from client: " + sb);
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
