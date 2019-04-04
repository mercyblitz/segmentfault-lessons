package com.segmentfault.deep.in.java.functional;

import java.util.function.Supplier;

public class SupplierDesignDemo {

    public static void main(String[] args) {

        echo("Hello,World"); // 固定参数

        echo(() -> { // 变化实现
            sleep(1000);
            return "Hello,World";
        });

        echo(SupplierDesignDemo::getMessage);
        // java 7 之前的写法
/*        echo(new Supplier<String>() {
            @Override
            public String get() {
              return  SupplierDesignDemo.getMessage();
            }
        });*/

        getMessage(); // 及时返回数据

        Supplier<String> message = supplyMessage(); // 待执行数据

        message.get(); // 实际执行
    }


    private static String getMessage() {
        sleep(1000);
        return "Hello,World";
    }

    private static Supplier<String> supplyMessage() {
        return SupplierDesignDemo::getMessage;
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void echo(String message) { // 拉的模式
        System.out.println(message);
    }

    public static void echo(Supplier<String> message) { // 推的模式
        System.out.println(message.get());
    }


}
