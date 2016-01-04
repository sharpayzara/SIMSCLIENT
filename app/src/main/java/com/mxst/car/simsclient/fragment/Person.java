package com.mxst.car.simsclient.fragment;

import com.mxst.car.simsclient.utils.PinYinUtils;

/**
 * author   Joy
 * Date:  2016/1/4.
 * version:  V1.0
 * Description:
 */
public class Person implements Comparable<Person> {

    private String path;
    private String brand;
    private String pinyin;

    public Person(String path, String brand ) {
        this.path = path;
        this.brand = brand;
        this.pinyin = PinYinUtils.getPinyin(brand);
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setPath(String path) {

        this.path = path;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPath() {
        return path;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public int compareTo(Person another) {
        return pinyin.compareTo(another.getPinyin());

    }
}
