package com.segmentfault.deep.in.java.reflection;

import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AccessibleObjectDemo {

    public static void main(String[] args) throws Exception {

        // AccessibleObject 是 Field、Method 和 Constructor 父类
        // 访问性检查 isAccessible()
        // 修改访问性 setAccessible(boolean)

        String value = "Hello,World";

        execute(() -> value.toString());

        Method toStringMethod = String.class.getMethod("toString");

        // 预热
        execute(() -> {
            try {
                // 默认情况任何 AccessibleObject 对象都会做访问性检查
                toStringMethod.invoke(value);
            } catch (Exception e) {
            }
        });

        execute(() -> {
            try {
                // 默认情况任何 AccessibleObject 对象都会做访问性检查
                toStringMethod.setAccessible(true);
                toStringMethod.invoke(value);
            } catch (Exception e) {
            }
        });

        execute(() -> {
            try {
                // 默认情况任何 AccessibleObject 对象都会做访问性检查
                // 因此，关闭此检查，可以提升性能
                toStringMethod.setAccessible(false);
                toStringMethod.invoke(value);
            } catch (Exception e) {
            }
        });
        // 反射调用

    }

    private static void execute(Runnable runnable) {
        int times = 100000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.printf("执行 %d 时间消耗：%d ms\n", times, costTime);

    }
}
