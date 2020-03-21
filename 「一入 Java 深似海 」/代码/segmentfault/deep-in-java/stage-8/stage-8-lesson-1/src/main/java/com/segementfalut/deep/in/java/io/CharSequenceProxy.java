package com.segementfalut.deep.in.java.io;

/**
 * 代理对象和被代理对象不一定存在层次关系或者不在同一个继承层次
 * 被代理的功能是被代理对象的子集
 */
public class CharSequenceProxy  {

    private final CharSequence delegate;

    public CharSequenceProxy(CharSequence delegate) {
        this.delegate = delegate;
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
