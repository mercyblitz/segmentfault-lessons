package com.segmentfault.deep.in.java.collection.advanced;

import java.util.Arrays;

public class ImmutableArrayDemo {

    public static void main(String[] args) {

        // values object address : 100
        Integer[] values = of(1, 2, 3);
        // 数组的特性，长度不变（特殊不变），内容可被替换
        // [0] = Integer.valueOf(1)
        // [1] = Integer.valueOf(2)
        // [2] = Integer.valueOf(3)

        // 数组的 Copy 与 集合对象的 Clone 是类似，浅克隆（复制）
        Integer[] valuesCopy = Arrays.copyOf(values, values.length);

        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i] == valuesCopy[i]);
        }
        // 修改复制后的内容
        valuesCopy[0] = 99;
        // 输出原始的数组
        System.out.println(Arrays.toString(values));

        Integer[] newValues = new Integer[3]; // 开辟 3 个元素大小的数组空间，malloc
        // [0] = null
        // [1] = null
        // [2] = null

        User[] users = of(1L, 2L, 3L);
        User[] usersCopy = Arrays.copyOf(users, values.length);

        // 假设 usersCopy 作为返回值对象的话，那么此时会不会有安全问题

        // 修改 usersCopy 的第一个 User 对象
        User user1 = usersCopy[0];
        user1.id = 99L;
        // 输出老 users，检查是否 users 的第一个对象会不会被修改
        System.out.println(Arrays.toString(users));
        // 结论：
        //      1. 数组的 Copy 与 集合对象的 Clone 是类似，浅克隆（复制）
        //      2. 如果需要只读数组的话， 需要深度 Clone（复制）
    }

    private static User[] of(Long... ids) {
        User[] users = new User[ids.length];
        for (int i = 0; i < ids.length; i++) {
            users[i] = new User(ids[i]);
        }
        return users;
    }

    private static class User {

        private Long id;


        private User(Long id) {
            this.id = id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    '}';
        }
    }

    private static Integer[] of(Integer... values) { // Integer... == Integer[]
        return values;
    }
}
