package com.segmentfault.deep.in.java.beans.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

/**
 * 支持泛型 {@link ApplicationEventListener 应用事件监听器}注册中心
 */
public class GenericApplicationEventListenerRegistry implements ApplicationEventListenerRegistry {

    /**
     * Key 是监听器的类型名称，Value 是多个相同类型的监听器
     */
    private Map<String, List<ApplicationEventListener<?>>> typedListeners
            = new LinkedHashMap<>();


    public void addApplicationEventListener(ApplicationEventListener<?> listener) {
        List<ApplicationEventListener<?>> listeners = getListeners(listener);
        listeners.add(listener);
    }

    protected List<ApplicationEventListener<?>> getListeners(ApplicationEventListener<?> listener) {

        Class<?> listenerClass = listener.getClass();
        Type[] genericInterfaces = listenerClass.getGenericInterfaces();

        String eventTypeName = Stream.of(genericInterfaces)
                .filter(t -> t instanceof ParameterizedType)// 判断接口是否 ParameterizedType 类型
                .map(t -> (ParameterizedType) t)             //  转换为 ParameterizedType
                // 判断 ApplicationEventListener 原始类型是否
                .filter(parameterizedType -> ApplicationEventListener.class.equals(parameterizedType.getRawType()))
                .map(parameterizedType -> {
                    // 获取第一个泛型参数
                    return parameterizedType.getActualTypeArguments()[0].getTypeName();
                })
                .findFirst()
                .orElse(null);

        return typedListeners.computeIfAbsent(eventTypeName, k -> new LinkedList<>());
    }

    public void removeApplicationEventListener(ApplicationEventListener<?> listener) {
        List<ApplicationEventListener<?>> listeners = getListeners(listener);
        listeners.remove(listener);
    }

    /**
     * @return 只读的 {@link ApplicationEventListener} 列表
     */
    public ApplicationEventListener[] getApplicationEventListeners() {
        return new ApplicationEventListener[0];
    }

    @Override
    public ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType) {
        String eventTypeName = eventType.getTypeName();
        return typedListeners.getOrDefault(eventTypeName, emptyList()).toArray(new ApplicationEventListener[0]);
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
