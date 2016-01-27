package com.mxst.car.simsclient.utils;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		PlatformConfig.setWeixin("wx97265ee4dec22093", "c4cf7cd23f2b9b4d348314a0df55f4ea");
		PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
	}
}
