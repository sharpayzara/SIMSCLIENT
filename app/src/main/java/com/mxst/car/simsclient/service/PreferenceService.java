package com.mxst.car.simsclient.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.mxst.car.simsclient.utils.Constant;

public class PreferenceService {
	private  SharedPreferences preferences; 
	private Context mContext;
	public SharedPreferences getPreferences() {
		return preferences;
	}

	public PreferenceService(Context context) {
		preferences = context.getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		mContext = context;
	}
	/**
	 * 记住当前版本已经登录过
	 */
	public void rememberLogin(){
    	Editor editor = preferences.edit();
    	editor.putBoolean("isFirstLogin", false);
    	editor.commit();
	}
	
	/**
	 * 记住推荐信息的刷新时间
	 */
	public void saveRefreshTime(){
		Editor editor = preferences.edit();
		editor.putLong("refreshTime", System.currentTimeMillis());
		editor.commit();
	}
	
	/**
	 * 保存用户名
	 * @param username
	 */
	public void saveUserName(String username){
    	Editor editor = preferences.edit();
    	editor.putString("username", username);
    	editor.commit();
	}
	/**
	 * 保存密码
	 * @param password
	 */
	public void savePassword(String password){
		Editor editor = preferences.edit();
		editor.putString("password", password);
		editor.commit();
	}
	public String getUserName(){
		return preferences.getString("username", "");
	}
	public String getPassword(){
		return preferences.getString("password", "");
	}
	public Long getLoginDate(){
		return preferences.getLong("loginDate",0);
	}
	public void saveLoginDate(Long loginDate){
		Editor editor = preferences.edit();
		editor.putLong("loginDate", loginDate);
		editor.commit();
	}

}
