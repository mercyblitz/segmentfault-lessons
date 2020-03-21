package com.segmentfault.deep.in.java.beans.event;

/**
 * {@link ApplicationEvent} 广播器
 */
public class ApplicationEventMulticaster {

    private final ApplicationEventListenerRegistry registry;

    public ApplicationEventMulticaster() {
        this.registry = new SimpleApplicationEventListenerRegistry();
    }

    public ApplicationEventMulticaster(ApplicationEventListenerRegistry registry) {
        this.registry = registry;
    }

    public void addApplicationEventListener(ApplicationEventListener<?> listener) {
        registry.addApplicationEventListener(listener);
    }

    public void removeApplicationEventListener(ApplicationEventListener<?> listener) {
        registry.removeApplicationEventListener(listener);
    }

    public ApplicationEventListener[] getApplicationEventListeners() {
        return registry.getApplicationEventListeners();
    }

    public ApplicationEventListener[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType) {
        return registry.getApplicationEventListeners(eventType);
    }

    public void multicastEvent(ApplicationEvent event) {
        // 逐一传递
        Class<? extends ApplicationEvent> eventType = event.getClass();
        for (ApplicationEventListener listener : getApplicationEventListeners(eventType)) {
            listener.onEvent(event);
        }
    }

    public static void main(String[] args) {

//        displaySimpleEvent();

        displayGenericEvent();

    }


    private static void displayGenericEvent() {

        ApplicationEventMulticaster multicaster =
                new ApplicationEventMulticaster(new GenericApplicationEventListenerRegistry());

        multicaster.addApplicationEventListener(new MyEventListener());
        multicaster.addApplicationEventListener(new MyEventListener2());

        // 传播的是 ApplicationEvent，MyEventListener 需要 MyEvent 类型，属于前者子类
        multicaster.multicastEvent(new MyEvent("2019"));
    }


    private static void displaySimpleEvent() {
        ApplicationEventMulticaster multicaster = new ApplicationEventMulticaster();
        // 注册一个事件监听器
        multicaster.addApplicationEventListener(event -> {
            System.out.println("处理事件-1 : " + event);
        });
        multicaster.addApplicationEventListener(event -> {
            System.out.println("处理事件-2 : " + event);
        });
        multicaster.addApplicationEventListener(event -> {
            System.out.println("处理事件-3 : " + event);
        });

        // 广播事件
        multicaster.multicastEvent(new ApplicationEvent("Hello,World"));
    }

}
