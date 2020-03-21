package com.segmentfault.deep.in.java.bytecode.bean;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

public class Person implements Nameable, Serializable {

    private static final long serialVersionUID = -1777979663507030142L;

    private Long id;

    private String name;

    private int age;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) throws PropertyVetoException {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        // 勉强属性（Constrained properties）必须在更新前执行
        // 强迫属性（Bound Properties）：当属性变化时，强制更新并且发送通知属性变化通知事件
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", updateTime=" + updateTime +
                '}';
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
    }
}
