package com.segementfalut.deep.in.java.io;


import java.util.stream.IntStream;

/**
 * 装饰器模式/包装模式
 * 当前装饰器模式实现类只有扩展不重要（并非无意义）的特性（方法），核心特性来自于继承的类或者接口（实际来源外部注入）
 * <p>
 * A 类型提供了 N 个方法（特性）
 * A 类型的装饰对象（Decorator）继承 A 类型
 * 代理对象（delegate） is A 类型
 * 装饰对象（Decorator）不一定完全按照代理对象（delegate）的行为来执行
 */
public class DecoratingCharSequence implements CharSequence {

    private final CharSequence delegate;

    public DecoratingCharSequence(CharSequence delegate) {
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
        return "@" + delegate.toString();
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
