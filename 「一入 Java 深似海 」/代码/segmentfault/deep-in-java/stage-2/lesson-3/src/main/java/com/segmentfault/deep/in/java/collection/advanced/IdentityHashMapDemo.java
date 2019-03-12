package com.segmentfault.deep.in.java.collection.advanced;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

public class IdentityHashMapDemo {

    public static void main(String[] args) {

        // 如果类覆盖了 Object 的 equals(Object) 方法，那么 hashCode() 方法需不需要覆盖？
        // 说明：不强制覆盖，建议实现，注意不要将 hashCode() 作为 equals 方法的实现，可参考
        // Objects.hash(Object...) 以及 Arrays.hashCode(Object[])，hashCode() 是一个计算较重的实现
        // equals 通常是做对象属性的比较

        // 如果类覆盖了 Object 的 hashCode() ，那么 equals(Object) 方法 方法需不需要覆盖？
        // 说明：必须实现，hashCode() 是用于 Hash 计算，比如普通的 HashMap，由于不同对象的 hashCode() 方法可能返回相同的数据
        // 原因一：int 数据范围 2^31-1，原因二：hashCode() 方法计算问题
        // 当不同对象 hashCode() 相同是，再做对象 equals(Object) 方法比较
        demoHashMap();

        // 场景，需要对对象本身做鉴别
        demoIdentityHashMap();

        // System.identityHashCode() 与 覆盖 hashCode() 方法的差异

//        Object a = new Object();
//        demoIdentityHashCodeAndHashCode(a, a);
//
//        Object b = new Object();
//        demoIdentityHashCodeAndHashCode(a, b);

        String string1 = "1";
        String string2 = "1";
//        demoIdentityHashCodeAndHashCode(string1, string2);

        // 不同对象
        string2 = new String("1");
        demoIdentityHashCodeAndHashCode(string1, string2);
    }

    private static void demoIdentityHashCodeAndHashCode(Object a, Object b) {

        System.out.printf("System.identityHashCode(%s) == %d \n", a, System.identityHashCode(a));
        System.out.printf("%s.hashCode() == %d \n", a, a.hashCode());

        System.out.printf("System.identityHashCode(%s) == System.identityHashCode(%s) == %s \n", a, b,
                System.identityHashCode(a) == System.identityHashCode(b));

        System.out.printf("%s.hashCode() == %s.hashCode() == %s \n", a, b, a.hashCode() == b.hashCode());
    }

    private static void demoMap(Map<String, Integer> map) {
        System.out.println("A" == new String("A")); // false
        System.out.println("A".equals(new String("A"))); // true
        System.out.println("A".hashCode() == new String("A").hashCode()); // true

        map.put("A", 1);
        map.put(new String("A"), 1);
        System.out.println(map.size());
    }

    private static void demoIdentityHashMap() {
        demoMap(new IdentityHashMap<>());
    }

    private static void demoHashMap() {
        demoMap(new HashMap<>());
    }

    private String name;

    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityHashMapDemo that = (IdentityHashMapDemo) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
