package com.segmentfault.deep.in.java.functional;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerLinghuDemo {

    public static void main(String[] args) {

        Consumer<String> consumer = ConsumerLinghuDemo::sayHi;

        consumer.accept("Hello,World");

        Consumer<String> consumer2 = ConsumerLinghuDemo::echo;

        // Fluent API
        // consumer2 -> consumer -> consumer
        consumer2.andThen(consumer).andThen(consumer2).accept("Hello,小马哥");

        Consumer<String> consumer3 = ConsumerLinghuDemo::sayHi2;

    }

    public  static void print(Consumer<? super CharSequence> cs, String message ){
        cs.accept(message);
    }


    private static void echo(String message) {
        System.out.println("echo : " + message);
    }

    private static void sayHi(String str){
        System.out.println("TEXT : "+ str);
    }

    private static Supplier<String> sayHi2(String str){
        return ConsumerLinghuDemo::getMessage;
    }
    private static String getMessage(){
        return "NIUBI";
    }
}
