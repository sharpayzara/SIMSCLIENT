package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.HomeInfoEntity;
import com.mxst.car.simsclient.layout.ClearEditText;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.CryptTool;

import org.json.JSONObject;

public class HomeFragment extends Fragment implements View.OnClickListener{
	RelativeLayout login_layout,user_layout;
	ClearEditText user_et,pwd_et;
	LayoutInflater inflater;
	Context mContext;
	TextView zx_title_1,zx_title_2,zx_content_1,zx_content_2;
	ImageView rmcx_iv_1,rmcx_iv_2,rmcx_iv_3,rmcx_iv_4,rmcx_iv_5,zx_iv_1,zx_iv_2;
	BitmapUtils utils;
	Button login_btn;
	private View root;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root =  inflater.inflate(R.layout.fragment_home, container, false);
		mContext = getActivity();
		this.inflater = inflater;
		initUI();
		initData();
		return root;
	}

	private void initUI() {
		login_layout = (RelativeLayout) root.findViewById(R.id.login_layout);
		user_layout = (RelativeLayout) root.findViewById(R.id.user_layout);
		rmcx_iv_1 = (ImageView) root.findViewById(R.id.rmcx_iv_1);
		rmcx_iv_2 = (ImageView) root.findViewById(R.id.rmcx_iv_2);
		rmcx_iv_3 = (ImageView) root.findViewById(R.id.rmcx_iv_3);
		rmcx_iv_4 = (ImageView) root.findViewById(R.id.rmcx_iv_4);
		rmcx_iv_5 = (ImageView) root.findViewById(R.id.rmcx_iv_5);
		zx_iv_1 = (ImageView) root.findViewById(R.id.zx_iv_1);
		zx_iv_2 = (ImageView) root.findViewById(R.id.zx_iv_2);
		zx_title_1 = (TextView) root.findViewById(R.id.zx_title_1);
		zx_title_2 = (TextView) root.findViewById(R.id.zx_title_2);
		zx_content_1 = (TextView) root.findViewById(R.id.zx_content_1);
		zx_content_2 = (TextView) root.findViewById(R.id.zx_content_2);
		user_et = (ClearEditText) root.findViewById(R.id.user_et);
		pwd_et = (ClearEditText) root.findViewById(R.id.pwd_et);
		login_btn = (Button) root.findViewById(R.id.login_btn);
		login_btn.setOnClickListener(this);

	}
	private void initData(){
		utils = new BitmapUtils(mContext);
		loadData();
	}


	private void loadData(){
		new BaseTask<JsonResult<HomeInfoEntity>,String>(mContext){

			@Override
			public TypeToken setTypeToken() {
				return new TypeToken<HomeInfoEntity>(){};
			}

			@Override
			public void onSuccess() {
				if(result.isSuccess()){
					if(result.getRecord().getHotBrand().size() >= 5){
						utils.display(rmcx_iv_1,result.getRecord().getHotBrand().get(0).getImg());
						utils.display(rmcx_iv_2,result.getRecord().getHotBrand().get(1).getImg());
						utils.display(rmcx_iv_3,result.getRecord().getHotBrand().get(2).getImg());
						utils.display(rmcx_iv_4,result.getRecord().getHotBrand().get(3).getImg());
						utils.display(rmcx_iv_5,result.getRecord().getHotBrand().get(4).getImg());

					}
					if(result.getRecord().getZxs().size() >= 2){
						utils.display(zx_iv_1,result.getRecord().getZxs().get(0).getImg());
						utils.display(zx_iv_2,result.getRecord().getZxs().get(1).getImg());
						zx_title_1.setText(result.getRecord().getZxs().get(0).getTitle());
						zx_title_2.setText(result.getRecord().getZxs().get(1).getTitle());
						zx_content_1.setText(result.getRecord().getZxs().get(0).getSubtitle());
						zx_content_2.setText(result.getRecord().getZxs().get(1).getSubtitle());

					}

				}
			}
		}.requestByPost(Constant.URL.INDEXINFO,new RequestParams());
	}

	@Override
	public void onClick(View v) {
		if(v == login_btn){
			if(!TextUtils.isEmpty(user_et.getText().toString())
					&& !TextUtils.isEmpty(pwd_et.getText().toString())){
				RequestParams params = new RequestParams();
				try {
					params.addQueryStringParameter("phone", CryptTool.md5Digest(user_et.getText().toString()));
					params.addQueryStringParameter("password",CryptTool.md5Digest(pwd_et.getText().toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}

				new BaseTask<JsonResult<JSONObject>,String>(mContext,R.string.login_notice){

					@Override
					public TypeToken setTypeToken() {
						return new TypeToken<JSONObject>(){};
					}

					@Override
					public void onSuccess() {
						if(result.isSuccess()){
							Constant.AUTHENTICATION_TOKEN = result.getRecord().optString("authenticationToken");
							CommonUtil.showToastToShort(mContext,"登录成功");
							login_layout.setVisibility(View.GONE);
							user_layout.setVisibility(View.VISIBLE);
						}else{
							CommonUtil.showToastToShort(mContext,result.getMsg());
						}
					}
				}.requestByPost(Constant.URL.LOGIN,params);
			}else{
				CommonUtil.showToastToShort(mContext,"用户名或密码不能为空！");
			}
		}
	}


}
