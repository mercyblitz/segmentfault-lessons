package com.segmentfault.deep.in.java.instrument;

import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestDemo {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ManifestDemo.class.getClassLoader();
        URL resource = classLoader.getResource("META-INF/MANIFEST.MF");
        Manifest manifest = new Manifest(resource.openStream());
        Attributes attributes = manifest.getAttributes("Manifest-Version");
        System.out.println(attributes);
    }
}
