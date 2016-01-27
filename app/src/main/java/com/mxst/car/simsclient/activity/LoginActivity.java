package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.layout.ClearEditText;
import com.mxst.car.simsclient.service.PreferenceService;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.CryptTool;
import com.mxst.car.simsclient.utils.TimeCountUtil;

import org.json.JSONObject;

import java.util.Date;

import cn.jpush.android.api.InstrumentedActivity;

public class LoginActivity extends InstrumentedActivity implements View.OnClickListener{
    Context mContext;
    ClearEditText phone_num;
    EditText password_num;
    Button obtain_password,login_btn;
    CountDownTimer timeCountUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        initUI();
    }

    private void initUI() {
        phone_num = (ClearEditText) findViewById(R.id.phone_num);
        password_num = (EditText) findViewById(R.id.password_num);
        obtain_password = (Button) findViewById(R.id.obtain_password);
        login_btn = (Button) findViewById(R.id.login_btn);
        obtain_password.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(obtain_password == v){
            if(phone_num.getText().toString().length() != 0){
                timeCountUtil = new TimeCountUtil(this, 60000, 1000, obtain_password);
                timeCountUtil.start();
                RequestParams params = new RequestParams();
                params.addQueryStringParameter("phone",phone_num.getText().toString());
                new BaseTask<JsonResult<String>,String>(mContext,R.string.download_notice){

                    @Override
                    public TypeToken setTypeToken() {
                        return new TypeToken<String>(){};
                    }

                    @Override
                    public void onSuccess() {
                        if(result.isSuccess()){
                            CommonUtil.showToastToShort(mContext,"密码已通过短信发送至你的手机");
                        }else{
                            CommonUtil.showToastToShort(mContext,result.getMsg());
                        }
                    }
                }.requestByPost(Constant.URL.OBTAIN_PASSWORD,params);
            }else{
                CommonUtil.showToastToShort(mContext,"请输入手机号");
            }
        }else if(login_btn == v){
            RequestParams params = new RequestParams();
            try {
                params.addQueryStringParameter("phone", CryptTool.md5Digest(phone_num.getText().toString()));
                params.addQueryStringParameter("password", CryptTool.md5Digest(password_num.getText().toString()));


                new BaseTask<JsonResult<JSONObject>,String>(mContext,R.string.login_notice){

                    @Override
                    public TypeToken setTypeToken() {
                        return new TypeToken<JSONObject>(){};
                    }

                    @Override
                    public void onSuccess() {
                        if(result.isSuccess()){
                            Constant.AUTHENTICATION_TOKEN = result.getRecord().optString("authenticationToken");
                            new PreferenceService(mContext).saveUserName(phone_num.getText().toString());
                            Constant.isLoginState = false;
                            PreferenceService ps = new PreferenceService(mContext);
                            ps.saveLoginDate(new Date().getTime());
                            CommonUtil.showToastToShort(mContext,"登录成功");
                            updateRedPoint();
                            setResult(Constant.REQUESTCODE.LOGINBACK);
                            finish();
                        }else{
                            CommonUtil.showToastToShort(mContext,result.getMsg());
                        }
                    }
                }.requestByPost(Constant.URL.LOGIN,params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constant.isLoginState = false;
    }

    public void updateRedPoint() {
        new BaseTask<JsonResult<JSONObject>,String>(this){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    Constant.POINTNUM = result.getRecord().optJSONObject("count").optInt("count");
                }
            }
        }.requestByPost(Constant.URL.NOTPAIDCOUNT,new RequestParams());
    }
}
