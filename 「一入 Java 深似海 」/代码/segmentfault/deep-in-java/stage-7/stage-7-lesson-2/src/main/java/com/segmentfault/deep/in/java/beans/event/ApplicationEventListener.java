package com.segmentfault.deep.in.java.beans.event;

import java.util.EventListener;

/**
 * 应用事件监听器
 *
 * @see EventListener
 * @param <E> ApplicationEvent 以及它的子类型
 */
public interface ApplicationEventListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 处理事件
     * @param event
     */
    void onEvent(E event);
}
