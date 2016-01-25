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
		PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
		PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
	}
}
