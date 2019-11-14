package com.segmentfault.deep.in.java.filesystem;

import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;

/**
 * 文件变化监听器
 */
@FunctionalInterface
public interface FileChangedListener extends EventListener, Observer, Consumer<FileChangedEvent> {

    void onChanged(FileChangedEvent event);

    default void accept(FileChangedEvent event) {
        this.onChanged(event);
    }

    default void update(Observable o, Object event) {
        if (event instanceof FileChangedEvent) {
            onChanged((FileChangedEvent) event);
        }
    }
}
