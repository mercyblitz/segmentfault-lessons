package com.segmentfault.deep.in.java.filesystem;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarFileDemo {

    public static void main(String[] args) throws IOException {
        Class<IOUtils> ioUtilsClass = IOUtils.class;
        URL jarFileURL = ioUtilsClass.getProtectionDomain().getCodeSource().getLocation();
        try (JarFile jarFile = new JarFile(jarFileURL.getPath())) {
            Manifest manifest = jarFile.getManifest();
            System.out.println(new HashMap(manifest.getMainAttributes()));
        }

//        Collections.list(zipFile.entries()).forEach(System.out::println);
    }
}
