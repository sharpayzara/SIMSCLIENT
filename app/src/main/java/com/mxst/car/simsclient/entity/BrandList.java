package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/5.
 * version:  V1.0
 * Description:
 */
public class BrandList {

    private String letter;

    private List<Person> data;

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }

    public String getLetter() {
        return letter;
    }

    public List<Person> getData() {
        return data;
    }
}
