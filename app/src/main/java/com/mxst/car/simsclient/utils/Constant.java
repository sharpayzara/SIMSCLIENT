package com.mxst.car.simsclient.utils;

public class Constant {
    public static final String APPID = "7ff3b5ddaf309007e16c13b8fafc1fd1";
    public static final String HOST = "http://222.177.210.200";   //测试
    public static final String CTX_PATH = HOST + "/public/";
    public static String userId;
    public static final String IP = "192.168.1.100";
    //public static final String IP = "192.168.1.101";
    public static final String PORT = "8080";

    public static final String SERVER = "http://" + IP + ":" + PORT + "/food_server";
    public static final String WEB_APP_URL = SERVER + "/";
    public static final String SERVER_LOGIN = "/Login?method=login";
    public static final String SERVER_REGISTER = "/RegisterAccount?method=RegisterAccount";

    //Btn的标识
    public static final int BTN_FLAG_HOME = 0x01;
    public static final int BTN_FLAG_INFO = 0x01 << 1;
    public static final int BTN_FLAG_REPAIR = 0x01 << 2;
    public static final int BTN_FLAG_FIND = 0x01 << 3;
    public static final int BTN_FLAG_MARKET = 0x01 << 4;

    //Fragment的标识
    public static final String FRAGMENT_FLAG_HOME = "首页";
    public static final String FRAGMENT_FLAG_INFO = "资讯";
    public static final String FRAGMENT_FLAG_REPAIR = "维修";
    public static final String FRAGMENT_FLAG_FIND = "找车";
    public static final String FRAGMENT_FLAG_MARKET = "积分商城";

    public static String AUTHENTICATION_TOKEN = "";

    public static class REQUESTCODE {
        public static final int CHOOSERES = 1;
        public static final int CHOOSEBRAND = 2;
        public static final int CHOOSESTORE = 3;
        public static final int CHOOSEMAN = 4;
    }

    public static class URL {
        public static final String LOGIN = CTX_PATH + "person/doLogin";
        public static final String GET_NEWS_LIST = CTX_PATH + "news/getNewsList";
        public static final String SEARCH_NEWS_LIST = CTX_PATH + "news/getNewsSearchList";
        public static final String INDEXINFO = CTX_PATH + "indexPage/getIndexInfo";
        public static final String BRANDLIST = CTX_PATH + "brand/brandList";
        public static final String QIANDAO = CTX_PATH + "indexPage/qianDao";
        public static final String UPDATEPERSON = CTX_PATH + "person/updatePerson";
        public static final String BRANDXINGHAOLIST = CTX_PATH + "brand/brandXinghaoList";
        public static final String BRANDRESOURCELIST = CTX_PATH + "brand/brandResourceList";
        public static final String FILTRATE = CTX_PATH + "brand/filtrate";
        public static final String NEWZXLIST = CTX_PATH + "collect/toNews";
        public static final String NEWZYLIST = CTX_PATH + "collect/toResources";
        public static final String SCORELIST = CTX_PATH + "indexPage/getJfRecord";
        public static final String NEWSLIST = CTX_PATH + "news/getNewsList";
        public static final String PARALIST = CTX_PATH + "brand/paraList";
        public static final String BRAND = CTX_PATH + "yy/brand";
        public static final String GETSALES = CTX_PATH + "cust/getSales";
        public static final String STORE = CTX_PATH + "yy/store";
        public static final String RECCUST = CTX_PATH + "cust/recCust";
    }

}
