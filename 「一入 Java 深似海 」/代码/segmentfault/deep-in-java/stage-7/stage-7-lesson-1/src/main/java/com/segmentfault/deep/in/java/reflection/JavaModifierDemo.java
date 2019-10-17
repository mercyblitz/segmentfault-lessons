package com.segmentfault.deep.in.java.reflection;

import java.io.Serializable;

public class JavaModifierDemo implements Serializable {

    private String data;

    private static final String NAME = "JavaModifierDemo";

    private static volatile Object object = new Object();

    private transient int hashCode; // 缓存 hash code，transient 属性不被序列化

    private static transient int a; // static 字段就是 transient

    protected JavaModifierDemo() {

    }

    static final synchronized <T> T[] of(T... values) {
        return values;
    }

    static native void copy(Object[] data);

    strictfp float add(float a, float b) {
        return a + b;
    }

    strictfp interface Data {

        public abstract String getData();

    }
}
