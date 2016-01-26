package com.mxst.car.simsclient.config;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/26.
 */
public class CommonBean implements Serializable{
    private String fieldName = "";//字段英文名
    private String fieldValue= "";//字段中文么

    public CommonBean(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
