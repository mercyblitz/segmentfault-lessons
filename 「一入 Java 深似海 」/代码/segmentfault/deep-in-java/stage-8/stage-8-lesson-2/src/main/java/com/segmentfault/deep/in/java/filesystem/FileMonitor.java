package com.segmentfault.deep.in.java.filesystem;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FileMonitor {

    /**
     * Key 被监视 File 对象
     */
    private Map<File, Long> lastModifiedSnapshot = new LinkedHashMap<>();

    private ScheduledExecutorService pollingWatchingService = Executors.newScheduledThreadPool(1);

    private FileChangedEventPublisher eventPublisher = new FileChangedEventPublisher();

    public void monitor(File monitoredFile) {
        updateLastModifiedSnapshot(monitoredFile);
        // 判断是否变化
        pollingWatchingService.scheduleAtFixedRate(() -> {
            long currentLastModified = monitoredFile.lastModified();
            long previousLastModified = lastModifiedSnapshot.putIfAbsent(monitoredFile, monitoredFile.lastModified());

            if (currentLastModified > previousLastModified) {
                eventPublisher.publish(monitoredFile);
                updateLastModifiedSnapshot(monitoredFile);
            }

        }, 0L, 5L, TimeUnit.SECONDS);
    }

    private void updateLastModifiedSnapshot(File monitoredFile) {
        lastModifiedSnapshot.put(monitoredFile, monitoredFile.lastModified());
    }

    public void addListeners(FileChangedListener listener, FileChangedListener... otherListeners) {
        eventPublisher.addFileChangedListener(listener);
        Stream.of(otherListeners).forEach(eventPublisher::addFileChangedListener);
    }

    public static void main(String[] args) {
        FileMonitor fileMonitor = new FileMonitor();

        fileMonitor.addListeners(event -> {
            System.out.println("处理文件变化事件：" + event);
        });

        fileMonitor.monitor(new File("D:\\temp"));

    }


}
