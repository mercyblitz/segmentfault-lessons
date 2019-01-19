package com.segmentfault.deep.in.java.method;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class MethodNameDemo {

    public static void main(String[] args) {

        // 方法表示执行动作，通常是动词
        // Runnable#run()
        // Action#execute()
        // Callable#call()

        // I am a person
        // I am a good person at childhood.

        // 动词+形容词
        // 副词形容动词或形容词
        Stream.of(1, 2, 3).forEachOrdered(System.out::println);

        //
        Lock lock = new ReentrantLock();
        try {
            lock.lockInterruptibly(); // 动词(verb.)+副词(adv.)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    // 动词+名词
    public List<String> getValues() {
        return Collections.emptyList();
    }

    // 动词+名词+副词
    public List<String> getValuesSynchronously() {
        return Collections.emptyList();
    }

    //
    public class ViewRender { // 名词

        public void render() { // 单一动词

        }

        // 同步渲染
        public void renderSynchronously() { // 动词+副词

        }

        // 并发渲染
        public void renderConcurrently() { // 动词+副词

        }
    }
}
