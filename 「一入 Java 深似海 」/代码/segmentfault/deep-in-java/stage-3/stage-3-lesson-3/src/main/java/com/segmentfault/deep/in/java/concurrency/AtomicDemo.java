package com.segmentfault.deep.in.java.concurrency;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicDemo {

    public static void main(String[] args) throws Exception {
        // CAS 是一种相对（MB）较重的比较，轻量级的锁（标量）

        getUnsafe();

        // volatile 修饰复杂对象类型时，不具备被传递到字段安全
    }

    private static void demoAtomicLong() {
        // long 表示 64位（8字节），高和低两端
        // long 并非线程安全，就是 volatile（高和低操作时，至少两次操作）
        AtomicLong l = new AtomicLong();
        // set 和 get 通过 JVM 特殊操作，不在直接写入和获取
    }

    private static void demoAtomicInteger() {
        // int 表示 int(32), char(16), short(16), byte(8), boolean(1)
        // AtomicInteger 内部使用 int 作为变量标识，1 表示 true, 0 表示 false
        // AtomicInteger set 和 get 操作均为 volatile 语义（MB）
        // AtomicInteger CAS Unsafe(Java 8) 和 VarHandle（Java 9+）
    }

    private static void demoAtomicBoolean() {
        AtomicBoolean b = new AtomicBoolean();
        // AtomicBoolean 内部使用 int 作为变量标识，1 表示 true, 0 表示 false
        // AtomicBoolean set 和 get 操作均为 volatile 语义（MB）
        // AtomicBoolean CAS Unsafe(Java 8) 和 VarHandle（Java 9+）
    }

    private static void getVarHandle() {
        MethodHandles.Lookup l = MethodHandles.lookup();
    }

    private static void getUnsafe() throws PrivilegedActionException {
        // 无法通过正常方法调用
//        Unsafe unsafe = Unsafe.getUnsafe();

        // https://github.com/mercyblitz/confucius-commons/blob/master/confucius-commons-lang/src/main/java/org/confucius/commons/lang/misc/UnsafeUtils.java
        final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
            public Unsafe run() throws Exception {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            }
        };

        Unsafe unsafe = AccessController.doPrivileged(action);

        if (unsafe == null) {
            throw new NullPointerException();
        }

    }
}
