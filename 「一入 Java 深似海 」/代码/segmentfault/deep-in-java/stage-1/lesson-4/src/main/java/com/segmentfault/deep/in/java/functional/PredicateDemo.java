package com.segmentfault.deep.in.java.functional;

import java.io.File;
import java.io.FileFilter;
import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args) {

        Predicate<File> predicate = file -> true;


    }
}
