package com.socketdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * socket 客户端
 * @ClassName: SocketClient
 * @Description:
 * @Author: lin
 * @Date: 2019/10/16 13:46
 * History:
 * @<version> 1.0
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message="你好  lin";
        socket.getOutputStream().write(message.getBytes("UTF-8"));
        outputStream.close();
        socket.close();
    }
}
