package com.segmentfault.deep.in.java.OptionalDemo;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 了解Optional的基本使用方式
 */
public class optionalDemo{


    public static void main(String[] args) {
        // 测试 of 与 ofNullable 方法
        //whenCreateEmptyOptional_thenNull();
        // 测试 orelse  和 orElseget()
        whenEmptyValue_thenReturnDefault();
    }
    public static void whenCreateEmptyOptional_thenNull(){
        //Optional<User> optional = Optional.empty(); will throw noSuchElementException
        User user = new User();
        user.setAge(10);
        user.setName("LH");
        // if the param is null，so the of() method  will throw nullpointException
        User user1 = null;
        //Optional<User> optionalUser = Optional.of(user1);
        // if the param is null , so the ofNullable() method will throw noSuchElementException
        // 即为我们之前empty方法
        Optional<User> optionalUser = Optional.ofNullable(user);
        // isPresent 方法会返回一个boolean值 为true 则说明不是null
        //System.out.println("optionalUser.isPresent() =  " +optionalUser.isPresent());
        Consumer<User> consumer  = optionalDemo::consumerUser;
        // ifPresent 方法 如果不为null 则执行consumer参数的lambda 否则会抛出NoSuchElementException
         optionalUser.ifPresent(consumer);

    }

    public static void whenEmptyValue_thenReturnDefault(){
        User user = null;
        User user2 = new User("not null",22);
        // orElse的意思是为null 则返回 orElse 的参数里的内容
        User result = Optional.ofNullable(user).orElse(user2);
        //第二个同类型的 API 是 orElseGet() —— 其行为略有不同。
        // 这个方法会在有值的时候返回值，如果没有值，它会执行作为参数传入的 Supplier(供应者) 函数式接口，并将返回其执行结果：
        Supplier<User> supplier = optionalDemo::supplier;
        User result2  = Optional.ofNullable(user).orElseGet(supplier);
        System.out.println(result);
        System.out.println(result2);
    }
    public static void consumerUser(User user){
        System.out.println(user);
    }
    public static User supplier(){
        return new User("supplier",22);
    }
}
class User{
    private  String name;
    private   int age;
    public User(){}
    public User(String name,int age){
        this.name=name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}