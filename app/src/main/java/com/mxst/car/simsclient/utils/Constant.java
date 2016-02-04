package com.mxst.car.simsclient.utils;

public class Constant {
    public static final String APPID = "7ff3b5ddaf309007e16c13b8fafc1fd1";
   // public static final String HOST = "http://222.177.210.200";   //测试
    public static final String HOST = "http://113.204.104.110";

    public static final String CTX_PATH = HOST + "/public/";
    public static String userId;
    public static final String IP = "192.168.1.100";
    //public static final String IP = "192.168.1.101";
    public static final String PORT = "8080";
    public static boolean isLoginState = false;
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
    public static final String FRAGMENT_FLAG_REPAIR = "会员服务中心";
    public static final String FRAGMENT_FLAG_FIND = "找车";
    public static final String FRAGMENT_FLAG_MARKET = "购物车";

    public static int POINTNUM = 0;

    //保存到本地的目录 (头像)
    public static final String SAVE_DIRECTORY = "/SIMS";
    //保存到本地图片的名字
    public static final String SAVE_PIC_NAME = "head.jpg";

    public static String AUTHENTICATION_TOKEN = "";
    public static final String SHAREDPREFERENCES_NAME = "simsUser";

    public static class REQUESTCODE {

        public static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
        public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
        public static final int PHOTO_REQUEST_CUT = 3;// 结果


        public static final int CHOOSERES = 1;
        public static final int CHOOSEBRAND = 2;
        public static final int CHOOSESTORE = 3;
        public static final int CHOOSEMAN = 4;
        public static final int LOGINBACK = 10;
        public static final int CHOOSEJG = 11;
        public static final int ORDEREPIREBACK = 12;
        public static final int NICKNAME = 13;

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
        public static final String LOAD_SERVICE = CTX_PATH + "indexPage/index";
        public static final String ARTISANCOMMONT = CTX_PATH + "artisan/artisanComment";
        public static final String ORDER_REPAIRE_LIST = CTX_PATH + "/yy/yyList";
        public static final String GETSALES = CTX_PATH + "cust/getSales";
        public static final String GETJG = CTX_PATH + "yy/jigong";
        public static final String STORE = CTX_PATH + "yy/store";
        public static final String RECCUST = CTX_PATH + "cust/recCust";
        public static final String INSURANCE_CAR_LIST = CTX_PATH + "insurance/getCarList";
        public static final String GETLIST = CTX_PATH + "repair/schedule/getList";
        public static final String OBTAIN_PASSWORD = CTX_PATH + "message/send";
        public static final String GETDETAIL = CTX_PATH + "repair/schedule/getDetail";
        public static final String GETWXDORWXJC = CTX_PATH + "repair/schedule/getWxdOrWxjc";
        public static final String INSUREdETAIL = CTX_PATH + "insurance/getBxList";
        public static final String GETCARLIST = CTX_PATH + "repaire/history/getCarList";
        public static final String GETWXLIST = CTX_PATH + "repaire/history/getWxList";
        public static final String JIGONG = CTX_PATH + "yy/jigong";
        public static final String SAVEYY = CTX_PATH + "yy/saveyy";
        public static final String ARTISANDETAIL = CTX_PATH + "artisan/artisanDetail";
        public static final String INSUREITEMDETAIL = CTX_PATH + "insurance/getBxDetail";
        public static final String COLLECT = CTX_PATH + "brand/collect";
        public static final String GETCOLLECTSTATUS = CTX_PATH + "news/getCollectStatus";
        public static final String SAVEEVALUATE = CTX_PATH + "artisan/saveComment";
        public static final String PERSONINFO = CTX_PATH + "person/getPersonInfo";
        public static final String WXLIST = CTX_PATH + "wx/wxList";
        public static final String WXDETAIL = CTX_PATH + "wx/wxDetail";
        public static final String DIKOU = CTX_PATH + "wx/dikou";
        public static final String TRADELIST = CTX_PATH + "cust/getcjCusts";
        public static final String FINDCUSTS = CTX_PATH + "indexPage/findCusts";
        public static final String JFDETAIL =  CTX_PATH + "indexPage/getJfDetail";
        public static final String NOTPAIDCOUNT =  CTX_PATH + "wx/notPaidCount";
    }

}
