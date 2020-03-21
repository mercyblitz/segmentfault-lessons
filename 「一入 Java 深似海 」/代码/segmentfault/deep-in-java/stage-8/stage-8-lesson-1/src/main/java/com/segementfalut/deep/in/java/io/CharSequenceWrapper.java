package com.segementfalut.deep.in.java.io;


import java.util.stream.IntStream;

/**
 * 装饰器模式/包装模式
 *
 */
public class CharSequenceWrapper implements CharSequence {

    private final CharSequence delegate;

    public CharSequenceWrapper(CharSequence delegate) {
        this.delegate = delegate;
    }

    public int length() {
        return delegate.length();
    }

    public char charAt(int index) {
        return delegate.charAt(index);
    }

    public CharSequence subSequence(int start, int end) {
        return delegate.subSequence(start, end);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    public IntStream chars() {
        return delegate.chars();
    }

    public IntStream codePoints() {
        return delegate.codePoints();
    }

    public String getDescription() {
        return toString();
    }
}
