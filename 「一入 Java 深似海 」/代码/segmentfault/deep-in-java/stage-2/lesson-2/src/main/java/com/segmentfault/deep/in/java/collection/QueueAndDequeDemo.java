package com.segmentfault.deep.in.java.collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueAndDequeDemo {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        Queue<String> queue = new LinkedList<>();
        Deque<String> deque= new LinkedList<>();
        queue.add("A");
        queue.offer("B");
    }
}
