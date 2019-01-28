package com.segmentfault.deep.in.java.functional;

public class FunctionalInterfaceDemo {

    public static void main(String[] arg) {


        Function1 function1 = () -> {

        };

        FunctionalInterfaceWithoutAnnotation f2 = () -> {

        };


    }

    @FunctionalInterface
    public interface Function1 {

        public abstract void execute();

        // 不能出现两次抽象方法定义
//        void execute2();

        default String getDescription() {
            return String.valueOf(this);
        }
    }

    //    @FunctionalInterface // @FunctionalInterface 并非必选的
    public interface FunctionalInterfaceWithoutAnnotation {

        void execute();
    }

//    @FunctionalInterface // @FunctionalInterface 只能描述接口
//    public class FunctionClass {
//
//    }
}
