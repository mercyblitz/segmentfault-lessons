package com.segmentfault.java.lesson1;

import java.util.logging.Logger;

public class FunctionMain {

    /**
     * < Java 9
     * public : all
     * protected : 继承 + 同包
     * (default) : 同包
     * private   : 当前类
     * <p>
     * >= Java 9
     */

    private static final Logger logger = Logger.getLogger("abc");


    public static void main(String[] args) {
        // byte(8) char(16) short(16) int(32) long(64)

        try {
            execute();
        } catch (Throwable e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private static void execute() throws Throwable {

    }
}

