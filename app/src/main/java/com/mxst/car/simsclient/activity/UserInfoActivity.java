package com.mxst.car.simsclient.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

import java.util.Calendar;

public class UserInfoActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    LinearLayout headImg_layout;
    EditText name,nickName,email,address;
    ImageButton submit_btn,cancel_btn;
    RadioButton sex_man,sex_woman;
    TextView birthday;
    Context mContext;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_info);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("个人中心");
        name = (EditText) findViewById(R.id.name);
        sex_man = (RadioButton) findViewById(R.id.sex_man);
        sex_woman = (RadioButton) findViewById(R.id.sex_woman);
        nickName = (EditText) findViewById(R.id.nickName);
        email = (EditText) findViewById(R.id.email);
        birthday = (TextView) findViewById(R.id.birthday);
        address = (EditText) findViewById(R.id.address);
        submit_btn = (ImageButton) findViewById(R.id.submit_btn);
        cancel_btn = (ImageButton) findViewById(R.id.cancel_btn);
        submit_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        sex_man.setChecked(true);
        calendar = Calendar.getInstance();
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {

                        //更新EditText控件日期 小于10加0
                        birthday.setText(new StringBuilder().append(mYear).append("-")
                                .append((mMonth + 1) < 10 ? 0 + (mMonth + 1) : (mMonth + 1))
                                .append("-")
                                .append((mDay < 10) ? 0 + mDay : mDay) );
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH) ).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(submit_btn == v){
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("name",name.getText().toString());
            params.addQueryStringParameter("nickName",nickName.getText().toString());
            if(sex_woman.isChecked()){
                params.addQueryStringParameter("sex","女");
            }else{
                params.addQueryStringParameter("sex","男");
            }
            params.addQueryStringParameter("email",email.getText().toString());
            params.addQueryStringParameter("birthday",birthday.getText().toString());
            params.addQueryStringParameter("address",address.getText().toString());

            new BaseTask<JsonResult<String>,String>(mContext,R.string.upload_notice){

                @Override
                public TypeToken setTypeToken() {
                    return new TypeToken<JSONObject>(){};
                }

                @Override
                public void onSuccess() {
                    if(result.isSuccess()){
                        CommonUtil.showToastToShort(mContext,"修改成功");
                    }else{
                        CommonUtil.showToastToShort(mContext,result.getMsg());
                    }
                }
            }.requestByPost(Constant.URL.UPDATEPERSON,params);
        }else if(v == cancel_btn){

        }
    }
}

