package com.segmentfault.deep.in.java.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import static com.segmentfault.deep.in.java.nio.BufferDemo.displayBufferMetadata;

public class CharBufferDemo {

    public static void main(String[] args) {

        // [X,X,X,X,X,X,X,X]
        //  p
        CharBuffer buffer = CharBuffer.allocate(8);
        // mark(-1),position(0),limit(8),capacity(8)

        // 当内容小于 8 字节
        String message = "Hello";
        // [X,X,X,X,X,X,X,X]
        // [H,e,l,l,o,X,X,X]
        //                l
        //            p   c
        // flip() -> limit = position  + rewind()
        // [H,e,l,l,o,X,X,X]
        //            l
        //m p             c
        for (char c : message.toCharArray()) {
            buffer.put(c);
            // 若不执行 flip() 操作，
            // rewind()
            // p = 0 -> limit(8) 循环
        }

        displayBufferMetadata(buffer);

        // 如果执行 flip() 操作，
        // limit = 8 -> 5
        // p = 0 -> limit(5) 循环
        buffer.flip();
        displayBufferMetadata(buffer);

        // 依次读取 Buffer 内有效元素
        while(buffer.hasRemaining()) { // 循环 5 次
            System.out.print(buffer.get());
        }
        System.out.println(); // 有意换行

        displayBufferMetadata(buffer);
    }

}
