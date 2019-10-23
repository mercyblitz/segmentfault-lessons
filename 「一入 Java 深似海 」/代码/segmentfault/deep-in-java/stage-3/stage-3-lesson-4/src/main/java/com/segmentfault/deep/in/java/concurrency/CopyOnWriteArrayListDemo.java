package com.segmentfault.deep.in.java.concurrency;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(); // Diamond 语法

        // main 线程插入三条数据
        // 安装 Thread ID
        list.add(1);
        // 判断当前线程 ID == main.threadId
        list.add(2);
        list.add(3);
        // Copy
        // JDK 升级两大核心性能提升
        // 1. 数组
        // 2. String

        // ConcurrentModificationException
        int times = 0;
        Iterator iterator = list.iterator();
        while (iterator.hasNext() && times < 100) {
            iterator.next();
            list.add(2);
            times++;
        }
    }
}
