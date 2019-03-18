package com.segmentfault.java.lesson1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LambdaDemo {

    // SCFP = Supplier + Consumer + Function + Predicate
    // 四种模式（缺少 Action 模式）

    // Action 模式
    private static void showAction() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {

            }
        };

        Runnable runnable2 = () -> {
        };

        Runnable runnable3 = LambdaDemo::showConsumer;
    }

    // Supplier 模式
    private static void showSupplier() {

        String string = "Hello,World";

        Supplier<String> string2 = () -> "Hello,World";

        Supplier<String> string3 = () -> new Integer(2).toString();

    }

    // Consumer 模式
    private static void showConsumer() {

        // 匿名类 传统写法
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                println(evt);
            }
        };

        // Lambda 基本写法
        PropertyChangeListener listener2 = evt -> {
            println(evt);
        };

        // Lambda 简略写法
        // PropertyChangeListener#propertyChange(PropertyChangeEvent)
        // 属于有入参，没有返回，与 println(Object) 一样
        PropertyChangeListener listener3 = LambdaDemo::println;

    }

    // Function 模式
    private static void showFunction() {

        Function<String, Integer> f = LambdaDemo::compareTo;

    }

    private static Integer compareTo(String value) {
        return value.compareTo("Hello,World");
    }

    public static void main(String[] args) {

        Action a = () -> {

        };


    }

    private static void println(Object object) {
        System.out.println(object);
    }

    @FunctionalInterface
    public interface Action {

        void execute();

        default void doExecute() {
            execute();
        }

    }

    private static void stream() {

        Stream.of(1, 2, 3, 4, 5)
                .map(String::valueOf)
        ;

    }
}
