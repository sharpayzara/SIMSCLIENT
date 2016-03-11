package com.mxst.car.simsclient.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.NewsInfoActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";
	JSONObject object;
	public MyReceiver() {  

	}  
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
			try {
				object = new JSONObject(extras);
				if(!TextUtils.isEmpty(object.optString("id"))){
					Intent i = new Intent(context, NewsInfoActivity.class);
					i.putExtra("content",object.optString("subTitle"));
					i.putExtra("title",object.optString("title"));
					i.putExtra("id",object.optString("id"));
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}  


	// send msg to MainActivity  
	/** 
	 * 可能经常用到的一点，获取附加的自定义的字段 
	 *  
	 * @param context 
	 * @param bundle 
	 */  
	private void processCustomMessage(Context context, Bundle bundle) {  
		// if (MainActivity.isForeground) {//检查当前软件是否在前台  
		// 利用JPushInterface.EXTRA_MESSAGE 机械能推送消息的获取  
		String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);  


		// 可能经常用到的一点，获取附加的自定义的字段、  
		// 这个字符串就是Json的格式，用于自己的服务器给特定的客户端传递一些特定的属性和配置，  
		// 例如显示一些数字、特定的事件，或者是访问特定的网址的时候，使用extras  
		// 例如显示订单信息、特定的商品列表，特定的咨询网址  
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);  


		// 使用广播或者通知进行内容的显示  
		NotificationCompat.Builder builder = new NotificationCompat.Builder(  
				context);  
		builder.setContentText(message).setSmallIcon(R.mipmap.ic_launcher);
		builder.setContentTitle("Message");  
		builder.setDefaults(Notification.DEFAULT_SOUND);  


		Log.i("Jpush", extras + "~~");  

		int drawResId = R.mipmap.ic_launcher;
		int num = 0;  
		String title="hello";  
		int iconType=0;  

		/** 
		 * 自定义信息： 获取 
		 * */  
		if (extras != null) {  
			try {  
				JSONObject object = new JSONObject(extras);  
				num = object.optInt("num");  
				title=object.optString("title","hello");  
				iconType=object.optInt("iconType");
			} catch (JSONException e) {  
				// TODO Auto-generated catch block  
				e.printStackTrace();  
			}  


		}  

		builder.setContentText(title);  

		// 不同的节日不同的推送  图标  
		switch (iconType) {  
		case 0://推送图标1  
			drawResId=R.mipmap.ic_launcher;
			break;  
		case 1://推送图标2  
			drawResId=R.mipmap.ic_launcher;
			break;  


		default:  
			break;  
		}  

		builder.setSmallIcon(drawResId);  

		if (num > 0) {  
			builder.setNumber(num);  
		}  


		Notification notification = builder.build();  
		NotificationManager manager = (NotificationManager) context  
				.getSystemService(Context.NOTIFICATION_SERVICE);  


		manager.notify(1, notification);  


	}
}