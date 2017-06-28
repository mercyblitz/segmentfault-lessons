package com.segmentfault.segmentfaultlesson4;

import com.segmentfault.segmentfaultlesson4.servlet.MyServletRequestListener;
import com.segmentfault.segmentfaultlesson4.spring.boot.MyFilter2;
import com.segmentfault.segmentfaultlesson4.spring.boot.MyServlet2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.segmentfault.segmentfaultlesson4.servlet"})
public class SegmentfaultLesson4Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SegmentfaultLesson4Application.class, args);
    }

    @Bean
    public static ServletRegistrationBean servletRegistrationBean() {

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();

        servletRegistrationBean.setServlet(new MyServlet2());
        servletRegistrationBean.setName("my-servlet2");
        servletRegistrationBean.addUrlMappings("/spring-boot/myservlet2");
        servletRegistrationBean.addInitParameter("myname", "myvalue");

        return servletRegistrationBean;

    }

    @Bean
    public static FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new MyFilter2());
        filterRegistrationBean.addServletNames("my-servlet2");

        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);

        return filterRegistrationBean;

    }

    @Bean
    public static ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyServletRequestListener());
        return servletListenerRegistrationBean;
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(SegmentfaultLesson4Application.class);
        return builder;
    }


}
