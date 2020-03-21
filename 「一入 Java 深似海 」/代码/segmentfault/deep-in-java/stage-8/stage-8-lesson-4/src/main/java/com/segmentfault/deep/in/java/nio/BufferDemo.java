package com.segmentfault.deep.in.java.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferDemo {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(8);
        // mark(-1),position(0),limit(8),capacity(8)
        buffer.put((byte) 1); // // mark(-1),position(1),limit(8),capacity(8)
        displayBufferMetadata(buffer);
        // [1,X,X,X,X,X,X,X]
        //    p
        // position 倒回 0
        buffer.rewind(); // 倒带
        // [1,X,X,X,X,X,X,X]
        //  p
        System.out.println(buffer.get());

        // 当内容小于 8 字节
        //
    }

    public static void displayBufferMetadata(Buffer buffer) {
        System.out.printf("当前 Buffer[ type : %s ]  position = %d , limit = %d , capacity = %d%n",
                buffer.getClass().getName(),
                buffer.position(),
                buffer.limit(),
                buffer.capacity()
        );
    }
}
