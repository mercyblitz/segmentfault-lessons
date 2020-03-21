package com.segmentfault.deep.in.java.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelRedirect {

    public static void main(String[] args) throws IOException {
        // System.in
        // System.out
        // 传统 InputStream 和 OutputStream
//        copy(System.in, System.out);

        //
        ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        copy(readableByteChannel, writableByteChannel);
    }

    private static void copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4 * 1024); // 4 K字节数组（堆内存）
        while (readableByteChannel.read(buffer) != -1) {
            buffer.flip(); // 记录当前 buffer limit = position (已读数据长度）
            // 优化写入
            if (buffer.hasRemaining()) {
                writableByteChannel.write(buffer);
            }
            buffer.clear();
        }

    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[4 * 1024]; // 4 K字节数组（堆内存）
        int readLength = -1;
        while ((readLength = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, readLength);
        }
    }
}
