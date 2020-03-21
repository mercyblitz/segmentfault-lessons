package com.segmentfault.deep.in.java.bytecode.bean;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.beans.*;
import java.lang.reflect.Method;

import static com.segmentfault.deep.in.java.bytecode.bean.Person.isNumeric;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class CGLibDemo {

    public static void main(String[] args) throws Exception {

        // CGLIB 代理 Person 类
        Person person = (Person) newInstance(Person.class);

        // 添加 PropertyChangeListener
        person.addPropertyChangeListener(event -> {
            // 属性变化通知事件
            System.out.printf("监听到属性[%s] 内容变化（事件来源：%s），老值：%s，新值：%s\n",
                    event.getPropertyName(),
                    event.getSource(),
                    event.getOldValue(),
                    event.getNewValue()
            );
        });

        // 添加 VetoableChangeListener
        person.addVetoableChangeListener(event -> {
            String newValue = valueOf(event.getNewValue());
            if (isNumeric(newValue)) {
                throw new PropertyVetoException(
                        format("当前属性[%s]的新值[%s]不合法，不能为纯数字!", event.getPropertyName(), newValue), event);
            }
        });


        person.setName("小马哥");
        person.setName("mercyblitz");
        person.setName("123456789");

        System.out.println(person);
    }

    public static Object newInstance(Class<Person> personClass) {
        Enhancer enhancer = new Enhancer();
        MethodInterceptor methodInterceptor = new PublishingPropertyEventMethodInterceptor();
        enhancer.setSuperclass(personClass);
        enhancer.setCallback(methodInterceptor);
        Object enhancedBean = enhancer.create();
        return enhancedBean;
    }

}

class PublishingPropertyEventMethodInterceptor implements MethodInterceptor {

    private final transient PropertyChangeSupport propertyChangeSupport;

    private final transient VetoableChangeSupport vetoableChangeSupport;

    PublishingPropertyEventMethodInterceptor() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.vetoableChangeSupport = new VetoableChangeSupport(this);
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        // 获取父类 CGLib Class（新生成） extends Person
        String methodName = method.getName();

        if ("addPropertyChangeListener".equals(methodName)) { // 添加监听器方法
            long startTime =System.currentTimeMillis();
            addPropertyChangeListener((PropertyChangeListener) args[0]);
            long costTime = System.currentTimeMillis() - startTime;
        } else if ("addVetoableChangeListener".equals(methodName)) {
            addVetoableChangeListener((VetoableChangeListener) args[0]);
        } else if ("setName".equals(methodName) &&                  // 方法名判断
                void.class.equals(method.getReturnType()) && // 方法返回类型判断
                args.length == 1 &&                          // 方法参数数量
                args[0] instanceof String                     // 方法参数类型的判断
        ) {
            // 当 name 属性变化时，发送通知
            // 勉强属性（Constrained properties）:当属性变化不合适时，阻断属性更新，并且通知异常来说明
            String propertyName = "name";
            Person person = (Person) object;
            String oldValue = person.getName(); // 读取老值
            String newValue = (String) args[0];     // 修改后值(newValue)
            // 勉强属性（Constrained properties）必须在更新前执行
            // 校验规则：当名称为纯数字时，阻断更新
            // 当 PropertyVetoException 异常发生时
            fireVetoableChange(propertyName, oldValue, newValue);
            // 执行 Person.setName(String)
            methodProxy.invokeSuper(object, args);

            // 发布属性已经变化事件 - PropertyChangeEvent
            // 强迫属性（Bound Properties）：当属性变化时，强制更新并且发送通知属性变化通知事件
            firePropertyChange(propertyName, oldValue, newValue);

            return null;
        } else { // 其他方法不被拦截，所以直接调用 super 的方法即可
            return methodProxy.invokeSuper(object, args);
        }
        return null;
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
}
