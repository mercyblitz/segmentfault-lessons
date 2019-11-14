package com.segmentfault.deep.in.java.filesystem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

/**
 * 模拟 dir
 */
public class DirCommandDemo {

    private final File rootDirectory;

    public DirCommandDemo(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void execute() {
        // 2019-07-05  20:27    <DIR>          .android
        // date time <DIR/> length filename
        Stream.of(rootDirectory.listFiles())
                .map(file -> {
                    StringBuilder messageBuilder = new StringBuilder();
                    // Date + Time
                    long lastModified = file.lastModified();
                    String dir = file.isDirectory() ? "<DIR>" : "";
                    String length = file.isDirectory() ? "" : String.valueOf(file.length());
                    String fileName = file.getName();
                    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm");
                    String dateTime = format.format(new Date(lastModified));
                    return messageBuilder.append(dateTime)
                            .append(" ")
                            .append(dir)
                            .append(" ")
                            .append(length)
                            .append(" ")
                            .append(fileName)
                            .toString();
                }).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new DirCommandDemo(new File(System.getProperty("user.home"))).execute();
    }
}
