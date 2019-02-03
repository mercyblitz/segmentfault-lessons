package com.segmentfault.deep.in.java.modular;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

public class UnnamedModuleDemo {

    public static void main(String[] args) {
        MapUtils.isEmpty(Collections.emptyMap());
        StringUtils.isBlank("OK");
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext();
        context.close();
    }
}
