package com.mxst.car.simsclient.layout;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.mxst.car.simsclient.R;

public class NetNotConnetDialog extends Dialog{
	int layoutRes;
	static Context mContext;
	private static NetNotConnetDialog obj;
	public static NetNotConnetDialog getInsance(Context context) {
		
		if(obj == null || (mContext != null && mContext != context)){
			obj = new NetNotConnetDialog(context);
		}
		return obj;
	}
	public NetNotConnetDialog(Context context) {
		super(context, R.style.NetNotConnectDialog);
		mContext = context;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.net_not_connect_dialog);
		setCanceledOnTouchOutside(true);
	}
}
