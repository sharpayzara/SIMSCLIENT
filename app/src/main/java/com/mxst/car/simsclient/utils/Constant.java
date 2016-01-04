package com.mxst.car.simsclient.utils;

public class Constant {
	public static final String APPID = "7ff3b5ddaf309007e16c13b8fafc1fd1";

	/*public static final String Preferences_user= "user";
	public static final String Preferences_username = "username";
	public static final String Preferences_password = "password";*/

	public static String userId;
	public static final String IP = "192.168.1.100";
	//public static final String IP = "192.168.1.101";
	public static final String PORT = "8080";
	
	public static final String SERVER = "http://" + IP + ":" + PORT + "/food_server"; 
	public static final String WEB_APP_URL = SERVER + "/" ;
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

	public static class REQUESTCODE {

	}

}
