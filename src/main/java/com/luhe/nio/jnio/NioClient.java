package com.luhe.nio.jnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 20882));

        String message = "Hello, server!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        client.write(buffer);

        // 读取服务端响应
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int read = client.read(readBuffer);
        if (read > 0) {
            readBuffer.flip();
            String response = new String(readBuffer.array(), 0, readBuffer.limit());
            System.out.println("Server response: " + response);
        }

        client.close();
    }
}