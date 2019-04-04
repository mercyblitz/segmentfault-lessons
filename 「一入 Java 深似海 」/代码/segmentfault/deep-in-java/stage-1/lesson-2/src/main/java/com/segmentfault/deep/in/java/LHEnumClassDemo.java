package com.segmentfault.deep.in.java;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LHEnumClassDemo {
    public static void main(String[] args) {
        Stream.of(CountingDEMO.values())
                .forEach(System.out::println);
    }

    /**
     * 枚举类
     * 强类型约束（相对于常量）
     * 1.枚举在编译时会自动继承 java.lang.Enum 类
     * 2。枚举中的成员都是 public static final的
     * 3。jvm 虚拟机会为我们字节码提升一个方法，类似与我下面实现的 values（）
     */

}
final class CountingDEMO{

    public static final CountingDEMO ONE = new CountingDEMO(1);
    public static final CountingDEMO TWO = new CountingDEMO(2);
    public static final CountingDEMO THREE = new CountingDEMO(3);
    public static final CountingDEMO FOUR = new CountingDEMO(4);
    public static final CountingDEMO FIVE = new CountingDEMO(5);
    private int value;
    private CountingDEMO(int a){
        this.value = a ;
    };
    public static CountingDEMO[] values(){
        // Fields -> filter -> public static final fields -> get
        Counting[] values = null;
        Field[] fields = CountingDEMO.class.getDeclaredFields();
       return Stream.of(fields).filter(field -> {
            int modifiers = field.getModifiers();
            return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
        }).map(field -> {
            try {
                return (CountingDEMO) field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()).toArray(new CountingDEMO[0]);
    }

    @Override
    public String toString() {
        return "CountingDEMO{" +
                "value=" + value +
                '}';
    }
}