package com.segmentfault.deep.in.java.functional;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateDesignDemo {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Collection<Integer> even = filter(numbers, num -> num % 2 == 0);

        Collection<Integer> odd = filter(numbers, num -> num % 2 != 0);

        System.out.println(odd);

        System.out.println(even);

        Stream.of(1, 2, 3, 4, 5).filter(num -> num % 2 == 0).forEachOrdered(System.out::println);
    }

    private static <E> Collection<E> filter(Collection<E> source, Predicate<E> predicate) {
        // 集合类操作，请不要直接利用参数
        List<E> copy = new ArrayList<E>(source);
        Iterator<E> iterator = copy.iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (!predicate.test(element)) {
                iterator.remove();
            }
        }
        return Collections.unmodifiableList(copy);
    }
}
