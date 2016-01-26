package com.mxst.car.simsclient.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/26.
 */
public class ScoreDetailConfig {

    private static Map<String,List<String>> map = new HashMap<>();

    private static List<String> cjList = new ArrayList<>();
    private static List<String> nameList = new ArrayList<>();
    private static List<String> nameList = new ArrayList<>();
    private static List<String> nameList = new ArrayList<>();
    private static List<String> nameList = new ArrayList<>();
    private static List<String> nameList = new ArrayList<>();

    //public  List<String> nameList;
    static{
        nameList.add("id");
        nameList.add("name");
        nameList.add("name");
        nameList.add("name");
        nameList.add("name");
        nameList.add("name");
        nameList.add("name");
        nameList.add("name");
        nameList.add("name");



        map.put("id",nameList);
    }


    public ClassCommonToDoBean(String fieldName , String fieldCName){
        this.fieldName = fieldName;
        this.fieldCName = fieldCName;
    }
}
