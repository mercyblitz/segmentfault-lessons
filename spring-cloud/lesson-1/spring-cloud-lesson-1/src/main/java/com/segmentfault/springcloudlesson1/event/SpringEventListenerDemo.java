package com.segmentfault.springcloudlesson1.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring 事件/监听器 Demo
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyApplicationListener.class);

        // 增加监听器
//        context.addApplicationListener(new MyApplicationListener());
        // 注册 Configuration 的方式
//        context.register(MyApplicationListener.class);
        // 上下文启动
//        context.refresh();

        // 发布事件
        context.publishEvent(new MyApplicationEvent("Hello,World"));
        context.publishEvent(new MyApplicationEvent(1));
        context.publishEvent(new MyApplicationEvent(new Double(1.0)));

    }

    @Component
    private static class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
        @Override
        public void onApplicationEvent(MyApplicationEvent event) {

            System.out.printf("MyApplicationListener receives event source : %s \n", event.getSource());

        }
    }


    private static class MyApplicationEvent extends ApplicationEvent {

        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }
}
