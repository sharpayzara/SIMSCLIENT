package com.mxst.car.simsclient.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/26.
 */
public class ScoreDetailConfig {

    public static Map<String,List<CommonBean>> configMap = new HashMap<>();

    private static List<String> cjList = new ArrayList<>();

    private static List<CommonBean> buyCarInfoList = new ArrayList<>();
    private static List<CommonBean> qdInfoList = new ArrayList<>();
    private static List<CommonBean> zcInfoList = new ArrayList<>();
    private static List<CommonBean> gmbxInfoList = new ArrayList<>();
    private static List<CommonBean> wxjsInfoList = new ArrayList<>();
    private static List<CommonBean> tjbxInfoList = new ArrayList<>();//推荐保险详情详情
    private static List<CommonBean> tjcjInfoList = new ArrayList<>();//推荐成交详情
    private static List<CommonBean> tjkhInfoList = new ArrayList<>();//推荐有效客户详情

    //public  List<String> nameList;
    static{
        buyCarInfoList.add(new CommonBean("jfxw","积分类型"));
        buyCarInfoList.add(new CommonBean("produceDate","时间"));
        buyCarInfoList.add(new CommonBean("jfChange","积分数"));
        buyCarInfoList.add(new CommonBean("brand","品牌"));
        buyCarInfoList.add(new CommonBean("carType","车型"));
        buyCarInfoList.add(new CommonBean("niankuan","年款"));
        buyCarInfoList.add(new CommonBean("spec","规格"));
        buyCarInfoList.add(new CommonBean("kx","款型"));

        qdInfoList.add(new CommonBean("produceDate","时间"));
        qdInfoList.add(new CommonBean("jfChange","积分数"));
        qdInfoList.add(new CommonBean("jfxw","积分类型"));


        zcInfoList.add(new CommonBean("produceDate","时间"));
        zcInfoList.add(new CommonBean("jfChange","积分数"));
        zcInfoList.add(new CommonBean("jfxw","积分类型"));

        wxjsInfoList.add(new CommonBean("produceDate","时间"));
        wxjsInfoList.add(new CommonBean("jfChange","积分数"));
        wxjsInfoList.add(new CommonBean("jfxw","积分类型"));

        gmbxInfoList.add(new CommonBean("produceDate","时间"));
        gmbxInfoList.add(new CommonBean("jfChange","积分数"));
        gmbxInfoList.add(new CommonBean("jfxw","积分类型"));

        tjbxInfoList.add(new CommonBean("produceDate","时间"));
        tjbxInfoList.add(new CommonBean("jfChange","积分数"));
        tjbxInfoList.add(new CommonBean("jfxw","积分类型"));


        tjcjInfoList.add(new CommonBean("jfChange","积分数"));
        tjcjInfoList.add(new CommonBean("produceDate","时间"));
        tjcjInfoList.add(new CommonBean("store","门店"));
        tjcjInfoList.add(new CommonBean("cj_kx","款型"));
        tjcjInfoList.add(new CommonBean("cj_mj","年款"));
        tjcjInfoList.add(new CommonBean("name","客户姓名"));
        tjcjInfoList.add(new CommonBean("cj_spec","规格"));
        tjcjInfoList.add(new CommonBean("cj_xinghao","车型"));
        tjcjInfoList.add(new CommonBean("jfxw","积分类型"));
        tjcjInfoList.add(new CommonBean("cj_brand","品牌"));

        tjkhInfoList.add(new CommonBean("jfChange","积分数"));
        tjkhInfoList.add(new CommonBean("produceDate","时间"));
        tjkhInfoList.add(new CommonBean("store","门店"));
        tjkhInfoList.add(new CommonBean("rec_brand","品牌"));
        tjkhInfoList.add(new CommonBean("name","客户姓名"));
        tjkhInfoList.add(new CommonBean("rec_chexing","意向车型"));
        tjkhInfoList.add(new CommonBean("jfxw","积分类型"));

        configMap.put("会员购车",buyCarInfoList);
        configMap.put("签到",qdInfoList);
        configMap.put("注册",zcInfoList);
        configMap.put("维修结算",wxjsInfoList);
        configMap.put("购买保险",gmbxInfoList);
        configMap.put("推荐保险",tjbxInfoList);
        configMap.put("推荐成交",tjcjInfoList);
        configMap.put("推荐有效客户",tjkhInfoList);

    }

}
