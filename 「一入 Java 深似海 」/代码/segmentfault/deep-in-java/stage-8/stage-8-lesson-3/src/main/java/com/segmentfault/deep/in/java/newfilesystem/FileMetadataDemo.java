package com.segmentfault.deep.in.java.newfilesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

import static com.segmentfault.deep.in.java.newfilesystem.PathDemo.USER_DIR_LOCATION;

public class FileMetadataDemo {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(USER_DIR_LOCATION);
        UserPrincipal userPrincipal = Files.getOwner(path);
        System.out.printf("Path[%s]'s owner is : %s\n", path, userPrincipal);
    }
}
