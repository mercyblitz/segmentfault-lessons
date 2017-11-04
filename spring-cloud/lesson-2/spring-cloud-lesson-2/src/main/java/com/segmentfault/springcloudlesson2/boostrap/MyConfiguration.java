package com.segmentfault.springcloudlesson2.boostrap;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Bootstrap 配置 Bean
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since Configuration
 */
@Configuration
public class MyConfiguration implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        // 从 ConfigurableApplicationContext 获取 ConfigurableEnvironment 实例
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // 获取 PropertySources
        MutablePropertySources propertySources = environment.getPropertySources();
        // 定义一个新的 PropertySource，并且放置在首位
        propertySources.addFirst(createPropertySource());

    }

    private PropertySource createPropertySource() {

        Map<String, Object> source = new HashMap<>();

        source.put("name", "小马哥");

        PropertySource propertySource = new MapPropertySource("my-property-source", source);

        return propertySource;

    }
}
