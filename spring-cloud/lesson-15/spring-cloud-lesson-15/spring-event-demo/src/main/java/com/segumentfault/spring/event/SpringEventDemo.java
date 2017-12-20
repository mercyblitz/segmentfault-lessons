package com.segumentfault.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * Spring 事件 Demo
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class SpringEventDemo {

    public static void main(String[] args) {
        // 创建 Annotation 驱动的 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 EventConfiguration 到 Spring 应用上下文
        context.register(EventConfiguration.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // AnnotationConfigApplicationContext 也是 ApplicationEventPublisher
        ApplicationEventPublisher publisher = context;
        // 发布一个 MyApplicationEvent
        publisher.publishEvent(new MyApplicationEvent("Hello,World"));
    }

    private static class MyApplicationEvent extends ApplicationEvent {

        public MyApplicationEvent(String message) {
            super(message);
        }
    }

    @Configuration
    public static class EventConfiguration {

        /**
         * 监听 {@link MyApplicationEvent}
         *
         * @param event {@link MyApplicationEvent}
         */
        @EventListener
        public void onEvent(MyApplicationEvent event) {
            System.out.println("监听事件 : " + event);
        }

    }

}