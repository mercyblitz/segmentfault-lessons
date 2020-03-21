package com.segmentfault.deep.in.java.beans.event;

public interface ApplicationEventListenerRegistry {

    void addApplicationEventListener(ApplicationEventListener<?> listener);

    void removeApplicationEventListener(ApplicationEventListener<?> listener);

    ApplicationEventListener[] getApplicationEventListeners();

    ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType);
}
