package com.segmentfault.deep.in.java.functional.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {

        count(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        line();

        sort(6, 4, 7, 9, 8, 1, 3, 2, 5);
        line();

        sort((x, y) -> (x < y) ? 1 : ((x == y) ? 0 : -1),
                6, 4, 7, 9, 8, 1, 3, 2, 5);
        line();

        parallelSort(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    private static void line() {
        System.out.println();
    }

    private static void parallelSort(Integer... numbers) {
        Stream.of(numbers)
                .sorted()
                .parallel()
                .forEach(StreamDemo::println);
    }

    private static void println(Object object) {
        System.out.printf("[%s] : %s\n", Thread.currentThread().getName(), object);
    }


    private static void sort(Integer... numbers) {
        Stream.of(numbers)
                .sorted()
                .forEach(System.out::println);
    }

    private static void sort(Comparator<Integer> comparator, Integer... numbers) {
        Stream.of(numbers)
                .sorted(comparator)
                .forEach(System.out::println);
    }

    private static void count(Integer... numbers) {
        Stream.of(numbers)
                .reduce(Integer::sum) // 合并
                .map(String::valueOf)
                .ifPresent(System.out::println);
    }
}
