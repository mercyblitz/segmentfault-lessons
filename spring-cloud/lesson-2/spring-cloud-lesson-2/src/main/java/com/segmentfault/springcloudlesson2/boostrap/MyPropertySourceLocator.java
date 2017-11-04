package com.segmentfault.springcloudlesson2.boostrap;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 {@link PropertySourceLocator} 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since PropertySourceLocator
 */
public class MyPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {

        if (environment instanceof ConfigurableEnvironment) {

            ConfigurableEnvironment configurableEnvironment = ConfigurableEnvironment.class.cast(environment);

            // 获取 PropertySources
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            // 定义一个新的 PropertySource，并且放置在首位
            propertySources.addFirst(createPropertySource());

        }
        return null;
    }

    private PropertySource createPropertySource() {

        Map<String, Object> source = new HashMap<>();

        source.put("spring.application.name", "小马哥的 Spring Cloud 程序");
        // 设置名称和来源
        PropertySource propertySource = new MapPropertySource("over-bootstrap-property-source", source);

        return propertySource;

    }
}
