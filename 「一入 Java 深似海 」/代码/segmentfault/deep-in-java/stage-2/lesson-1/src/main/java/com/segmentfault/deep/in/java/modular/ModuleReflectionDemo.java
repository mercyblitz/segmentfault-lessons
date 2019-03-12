package com.segmentfault.deep.in.java.modular;

import java.lang.module.ModuleDescriptor;

public class ModuleReflectionDemo {

    public static void main(String[] args) {

        Class klass = ModuleReflectionDemo.class;

        Module module = klass.getModule();

        String moduleName = module.getName();

        System.out.printf("类 %s 存在于模块 : %s 之中\n", klass.getName(), moduleName);

        ModuleDescriptor moduleDescriptor = module.getDescriptor();

        moduleDescriptor.requires().stream().forEach(requires -> {
                    System.out.printf("requires 模块名称 %s，修饰符定义： %s \n", requires.name(), requires.modifiers());
                }
        );

        moduleDescriptor.exports().stream().forEach(exports -> {
            System.out.printf("exports 包名称 %s，targets： %s \n", exports.source(), exports.targets());
        });
    }
}
