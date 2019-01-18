package com.segmentfault.deep.in.java;

public class CloneableDemo {

    public static void main(String[] args) throws CloneNotSupportedException {

        String desc = "Hello,World";

        Data data = new Data();
        data.setValue(1);
        data.setDesc(desc);

        Data copy = data.clone();

        System.out.println("data == copy " + (data == copy));

        System.out.println(copy.hashCode() == data.hashCode());
        // 输出 1
        // 浅 Copy
        // 浅深主要在于对象类型
        System.out.println(copy.getValue());
        // 如果是浅拷贝的话
        // desc -> data.desc -> clone -> copy.desc == desc
        System.out.println(copy.getDesc() == desc);
        // 如果是深拷贝的话
        System.out.println(copy.getDesc().equals(desc));


    }


}

class Data extends Object implements Cloneable {

    private int value;

    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 通常把 protected 访问性提升为 public
     * 强制调整为目标类型
     *
     * @return
     * @throws CloneNotSupportedException
     */
    public Data clone() throws CloneNotSupportedException {
        Data copy = (Data) super.clone();
        // 原生类型没有浅深关系
        // 对象类型需要复制
        copy.desc = new String(this.desc);
        return copy;
    }

//    @Override
//    public int hashCode() {
//        return value;
//    }
}