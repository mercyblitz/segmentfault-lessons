package com.segmentfault.deep.in.java.newfilesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.segmentfault.deep.in.java.newfilesystem.PathDemo.USER_DIR_LOCATION;

public class TryWithResourcesDemo {

    public static void main(String[] args) {
        Path pomXmlPath = Paths.get(USER_DIR_LOCATION, "pom.xml");
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(pomXmlPath, charset)) {
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.err.format("IOException : %s\n", e.getMessage());
        }
//        finally {
//            if (reader != null) {
//                reader.close();
//            }
//        }
    }
}
