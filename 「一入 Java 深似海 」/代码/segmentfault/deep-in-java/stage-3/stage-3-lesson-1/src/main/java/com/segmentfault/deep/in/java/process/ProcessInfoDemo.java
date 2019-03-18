package com.segmentfault.deep.in.java.process;

import java.lang.management.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ProcessInfoDemo {

    public static void main(String[] args) {

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        long pid = ProcessHandle.current().pid();
        System.out.println("[Java 9 + 的方法] 当前进程 ID : " + pid);

        Instant instant = Instant.ofEpochMilli(runtimeMXBean.getStartTime());
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("当前进程启动时间：" + localDate);
        System.out.println("当前进程上线时间：" + runtimeMXBean.getUptime());
        System.out.println("当前进程线程数量：" + threadMXBean.getThreadCount());

        ManagementFactory.getMemoryManagerMXBeans().forEach(memoryManagerMXBean -> {

        });

        System.exit(9);
    }
}
