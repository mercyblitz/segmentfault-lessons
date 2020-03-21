package com.segmentfault.deep.in.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelServerDemo {
    // select 和 epoll
    public static void main(String[] args) throws IOException, InterruptedException {
        // 服务端 SocketChannel
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
            // 绑定服务端
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            System.out.println("当前服务器地址：" + serverSocketChannel.socket().getLocalSocketAddress());
            String message = "Hello,World";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    // 当连接建立时
                    System.out.printf("接受客户端[%s] 的连接...%n", socketChannel.getRemoteAddress());
                    buffer.rewind();
                    socketChannel.write(buffer); // 写入管道到发送 Socket 请求客户端
                    socketChannel.close();

                } else { // 非阻塞时，执行逻辑
                    Thread.sleep(500);
                }
            }
        }
    }
}
