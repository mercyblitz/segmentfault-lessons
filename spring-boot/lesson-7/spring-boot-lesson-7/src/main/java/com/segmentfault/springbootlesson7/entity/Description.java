package com.segmentfault.springbootlesson7.entity;


public class Description {

    private String province;

    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Description{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
