package com.segmentfault.deep.in.java;

public interface InterfaceFiledDemo {
        int a= 10;
       default void changea(){
           System.out.println(a);
       }
}
