package com.segmentfault.deep.in.java.beans.properties;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    private static final long serialVersionUID = -1777979663507030142L;

    private Long id;

    private String name;

    private int age;

    private Date updateTime;

    private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private final transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws PropertyVetoException {
        // 当 name 属性变化时，发送通知
        // 勉强属性（Constrained properties）:当属性变化不合适时，阻断属性更新，并且通知异常来说明
        String propertyName = "name";

        String oldValue = this.name; // 读取老值
        String newValue = name; // 修改后值(newValue)
        // 勉强属性（Constrained properties）必须在更新前执行
        // 校验规则：当名称为纯数字时，阻断更新
        // 当 PropertyVetoException 异常发生时
        fireVetoableChange(propertyName, oldValue, newValue);

        this.name = name; // this.name
        // 发布属性已经变化事件 - PropertyChangeEvent
        // 强迫属性（Bound Properties）：当属性变化时，强制更新并且发送通知属性变化通知事件
        firePropertyChange(propertyName, oldValue, newValue);
    }


    private void firePropertyChange(String propertyName, String oldValue, String newValue) {
//        PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
        // 广播事件
        // 得到所有 PropertyChangeEvent 监听器
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws PropertyVetoException {
        vetoableChangeSupport.fireVetoableChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return propertyChangeSupport.getPropertyChangeListeners();
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    public VetoableChangeListener[] getVetoableChangeListeners() {
        return vetoableChangeSupport.getVetoableChangeListeners();
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
}
