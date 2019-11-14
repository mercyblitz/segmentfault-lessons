package com.segmentfault.deep.in.java.filesystem;

import java.io.File;
import java.util.EventObject;

/**
 * 文件变化事件
 */
public class FileChangedEvent extends EventObject {

    public FileChangedEvent(File source) {
        super(source);
    }

    @Override
    public File getSource() {
        return (File) super.getSource();
    }
}
