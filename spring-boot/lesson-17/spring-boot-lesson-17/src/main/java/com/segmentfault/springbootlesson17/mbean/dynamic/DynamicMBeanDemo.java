package com.segmentfault.springbootlesson17.mbean.dynamic;

import com.segmentfault.springbootlesson17.mbean.SimpleData;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * MBean 演示代码
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.30
 */
public class DynamicMBeanDemo {

    public static void main(String[] args) throws Exception {

        // MBean 服务器 - Agent Level
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        // 注册对象
        SimpleDynamicMBean simpleDynamicMBean = new SimpleDynamicMBean();

        // 注册名称
        ObjectName objectName = createObjectName(simpleDynamicMBean);

        // 注册 MBean - SimpleDataMBean
        mBeanServer.registerMBean(simpleDynamicMBean, objectName);

        // 服务器不马上停止
        Thread.sleep(Long.MAX_VALUE);

    }

    private static ObjectName createObjectName(Object mbean) throws MalformedObjectNameException {

        Class<?> mBeanClass = mbean.getClass();

        // e,g : com.segmentfault.springbootlesson17.mbean
        String packageName = mBeanClass.getPackage().getName();
        String className = mBeanClass.getSimpleName();

        return new ObjectName(packageName + ":type=" + className);

    }

}
