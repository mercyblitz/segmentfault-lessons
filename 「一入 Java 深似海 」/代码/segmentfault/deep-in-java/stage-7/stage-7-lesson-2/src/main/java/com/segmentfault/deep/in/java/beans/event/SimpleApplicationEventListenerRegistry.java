package com.segmentfault.deep.in.java.beans.event;

import java.util.*;

/**
 * 简单 {@link ApplicationEventListener 应用事件监听器}注册中心实现
 */
public class SimpleApplicationEventListenerRegistry implements ApplicationEventListenerRegistry {

    private Set<ApplicationEventListener<?>> listeners = new TreeSet<>(new ApplicationEventListenerComparator());


    @Override
    public void addApplicationEventListener(ApplicationEventListener<?> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeApplicationEventListener(ApplicationEventListener<?> listener) {
        listeners.remove(listener);
    }

    /**
     * @return 只读的 {@link ApplicationEventListener} 列表
     */
    @Override
    public ApplicationEventListener[] getApplicationEventListeners() {
        return listeners.toArray(new ApplicationEventListener[0]);
    }

    @Override
    public ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType) {
        return new ApplicationEventListener[0];
    }

    /**
     * 通过 {@link ApplicationEventListener} 实现类去重
     */
    static class ApplicationEventListenerComparator implements Comparator<ApplicationEventListener> {

        @Override
        public int compare(ApplicationEventListener o1, ApplicationEventListener o2) {
            String oneClassName = o1.getClass().getName();
            String anotherClassName = o2.getClass().getName();
            return oneClassName.compareTo(anotherClassName);
        }
    }
}
