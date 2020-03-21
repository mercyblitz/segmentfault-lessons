package com.segementfalut.deep.in.java.io;

import java.io.Console;
import java.io.IOException;

public class CommandLineDemo {

    public static void main(String[] args) throws IOException {
        System.out.println("按任意键退出...");
        // 阻塞线程
        // 当前线程 [main]
        // Thread.wait();
        // I/O 线程等待用户输入 -> 通知 Thread.notify();
        System.in.read();

        Console console = System.console();

        while (true) {

            String line = console.readLine();

            if ("exit".equals(line)) {
                break;
            } else {
                System.out.println(line);
            }
        }
    }
}
