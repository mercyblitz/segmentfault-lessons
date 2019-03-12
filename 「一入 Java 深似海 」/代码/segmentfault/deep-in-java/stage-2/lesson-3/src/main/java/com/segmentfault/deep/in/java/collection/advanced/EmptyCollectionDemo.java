package com.segmentfault.deep.in.java.collection.advanced;

import java.util.Collections;
import java.util.List;

public class EmptyCollectionDemo {

    public static void main(String[] args) {

        // 对自己（严格）：所有的返回接口类型的方法禁止返回 null
        // 对别人（宽容）：要做 null 判断（尤其在 RPC 场景）

        // 集合方法入参：
        // 1. 如果能用 Iterable 尽量用
        // 2. 其次是 Collection
        // 3. 再者是 List 或 Set
        // 禁止使用具体类型，比如：ArrayList，LinkedHashSet
    }


    public static List<String> getIdsList(String name) {
        if(name ==null || name.length() < 1){
            return Collections.emptyList();
        }
        // 只读 empty List
        // 实现 Java 序列化
        return Collections.emptyList();
    }
}
