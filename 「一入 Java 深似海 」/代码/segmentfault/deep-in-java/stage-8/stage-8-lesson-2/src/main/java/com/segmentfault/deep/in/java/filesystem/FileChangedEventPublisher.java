package com.segmentfault.deep.in.java.filesystem;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * {@link FileChangedEvent} 事件发布器
 */
public class FileChangedEventPublisher extends Observable {

    public void publish(File changedFile) {
        publish(new FileChangedEvent(changedFile));
    }

    public void publish(FileChangedEvent event) {
        // 标记状态已经改变
        super.setChanged();
        super.notifyObservers(event);
    }

    public void addFileChangedListener(FileChangedListener listener) {
        addObserver(listener);
    }

    public static void main(String[] args) {
        FileChangedEventPublisher publisher = new FileChangedEventPublisher();

        publisher.addFileChangedListener(event -> {
            System.out.println("处理文件变化事件：" + event);
        });

        publisher.publish(new File("temp"));
    }
}
