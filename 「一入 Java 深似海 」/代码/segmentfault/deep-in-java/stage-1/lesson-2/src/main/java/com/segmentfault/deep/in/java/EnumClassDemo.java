package com.segmentfault.deep.in.java;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumClassDemo {

    public static void main(String[] args) {

        // Q1: THREE 到底是第几个定义的

//        println(Counting.ONE);
//        println(Counting.FIVE);

        // Q3: 为什么枚举会输出成员名称
//        println(CountingEnum.ONE);
//        println(CountingEnum.FIVE);
//
//        printEnumMeta(CountingEnum.ONE);
//        printEnumMeta(CountingEnum.FIVE);

        // 自定义实现 values()
        printCountingMembers();
        // Java 枚举字节码提升实现 values()
        printCountingEnumMembers();
    }

    // Q2: 能否输出所有的成员
    public static void println(Counting counting) {
        System.out.println(counting);
    }

    public static void println(CountingEnum countingEnum) {
        System.out.println(countingEnum);
    }

    public static void printEnumMeta(Enum enumeration) {
        // 说明任何枚举都扩展了 java.lang.Enum
        System.out.println("Enumeration type : " + enumeration.getClass());
        // Enum#name()  = 成员名称
        // Enum#ordinal() = 成员定义位置
        System.out.println("member : " + enumeration.name());
        System.out.println("ordinal : " + enumeration.ordinal());
        // values() 方法是 Java 编译器给枚举提升的方式
    }

    public static void printCountingMembers() {
        Stream.of(Counting.values())
                .forEach(System.out::println);
    }

    public static void printCountingEnumMembers() {
        Stream.of(CountingEnum.values())
                .forEach(System.out::println);
    }


}

enum CountingEnum implements Cloneable {

    ONE(1) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    },
    TWO(2) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    },
    THREE(3) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    },
    FOUR(4) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    },
    FIVE(5) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    };

    private int value;

    /* private */ CountingEnum(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CountingEnum : " + value;
    }

    public abstract String valueAsString();

    public int getValue() {
        return value;
    }
}

//class ExtendedEnum extends CountingEnum {
//
//}

class ExtendedCounting extends Counting {

    private ExtendedCounting(int value) {
        super(value);
    }

    @Override
    public String valueAsString() {
        return toString();
    }
}

/**
 * 枚举类：计数
 * 强类型约束（相对于常量）
 */
abstract class Counting extends Data implements Cloneable {

    public static final Counting ONE = new Counting(1) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    };

    public static final Counting TWO = new Counting(2) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    };

    public static final Counting THREE = new Counting(3) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    };

    public static final Counting FOUR = new Counting(4) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    };

    public static final Counting FIVE = new Counting(5) {
        @Override
        public String valueAsString() {
            return String.valueOf(this.getValue());
        }
    };

    private int value;

    protected Counting(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public abstract String valueAsString();

    @Override
    public String toString() {
        return "Counting : " + value;
    }

    public static Counting[] values() {
        // Fields -> filter -> public static final fields -> get
        return Stream.of(Counting.class.getDeclaredFields())
                .filter(field -> {
                    int modifiers = field.getModifiers();
                    return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
                }).map(field -> {
                    // Field -> Counting
                    try {
                        return (Counting) field.get(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()).toArray(new Counting[0]);

    }

}
