package com.segmentfault.deep.in.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServerDemo {

    public static void main(String[] args) throws IOException {
        // 服务端 SocketChannel
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
            // 绑定服务端
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            System.out.println("当前服务器地址：" + serverSocketChannel.socket().getLocalSocketAddress());
            // 打开 Selector
            Selector selector = Selector.open();
            // 注册 OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int n = selector.select();
                if (n == 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ssc.accept();
                        if (socketChannel == null) {
                            continue;
                        }
                        System.out.printf("接受客户端[%s] 的连接...%n", socketChannel.getRemoteAddress());
                        ByteBuffer buffer = ByteBuffer.allocate(8);
                        // 将服务器当前 timestamp 传递到客户端
                        buffer.putLong(System.currentTimeMillis());
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            socketChannel.write(buffer);
                        }
                        socketChannel.close();
                        System.out.println("当前服务器时间已发送到客户端");
                    }
                    iterator.remove(); // 移除已接受 SelectionKey
                }
            }
        }
    }
}
