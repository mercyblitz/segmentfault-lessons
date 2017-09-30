package com.segmentfault.springbootlesson17.mbean;

/**
 * 简单数据 MBean
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.30
 */
public interface SimpleDataMBean {


    /**
     * Setter
     * Property
     * 属性
     * @param data
     */
    void setData(String data);

    /**
     * Getter
     * 属性
     * @return
     */
    String getData();


    /**
     * 展示数据
     * 操作(Operation)
     * @return
     *
     */
    String displayData();

}
