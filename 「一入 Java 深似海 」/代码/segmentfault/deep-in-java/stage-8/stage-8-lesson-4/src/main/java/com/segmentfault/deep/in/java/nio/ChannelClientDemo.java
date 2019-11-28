package com.segmentfault.deep.in.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelClientDemo {
    // select 和 epoll
    public static void main(String[] args) throws IOException, InterruptedException {
        // 客户端 SocketChannel
        try (SocketChannel socketChannel = SocketChannel.open();) {
            // 设置为非阻塞
            socketChannel.configureBlocking(false);
            // 连接服务端
            socketChannel.connect(new InetSocketAddress(8080));
            while (!socketChannel.finishConnect()) {
                System.out.println("等待连接到达...");
            }
            ByteBuffer buffer = ByteBuffer.allocate(8);
            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char)buffer.get());
                }
                buffer.clear();
            }
        }
    }
}
