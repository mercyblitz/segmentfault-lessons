package com.segmentfault.deep.in.java.filesystem;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileDemo {

    public static void main(String[] args) throws IOException {
        Class<IOUtils> ioUtilsClass = IOUtils.class;
        URL jarFileURL = ioUtilsClass.getProtectionDomain().getCodeSource().getLocation();
        try (ZipFile zipFile = new ZipFile(jarFileURL.getPath(), Charsets.toCharset("UTF-8"))) {
            ZipEntry manifestEntry = zipFile.getEntry("META-INF/MANIFEST.MF");
            try (InputStream inputStream = zipFile.getInputStream(manifestEntry)) {
                System.out.println(IOUtils.toString(inputStream, "UTF-8"));
            }
        }

//        Collections.list(zipFile.entries()).forEach(System.out::println);
    }
}
