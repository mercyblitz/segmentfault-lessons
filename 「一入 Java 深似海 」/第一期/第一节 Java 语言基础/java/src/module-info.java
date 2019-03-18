module lesson1 {
    requires java.base; // 默认依赖 java.base
//    requires java.sql;  // 依赖 SQL（JDBC）
    requires java.logging;
    exports com.segmentfault.java.lesson1;
    exports com.segmentfault.java;
}