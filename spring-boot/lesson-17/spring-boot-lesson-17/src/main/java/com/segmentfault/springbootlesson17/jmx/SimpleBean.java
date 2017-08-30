package com.segmentfault.springbootlesson17.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * 简单的Bean
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.30
 */
@ManagedResource(
        objectName = "com.segmentfault.springbootlesson17.jmx:type=SimpleBean",
        description = "这是一个简单的Bean，被 Spring 托管"
)
@Component
public class SimpleBean {

    private Long id;

    private String name;

    private Integer value;

    @ManagedAttribute(description = "ID 属性")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManagedAttribute(description = "Name 属性")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManagedAttribute(description = "Value 属性")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @ManagedOperation(description = "display 操作")
    public String display() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
