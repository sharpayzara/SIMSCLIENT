package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

public class UserActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    Context mContext;
    LinearLayout headImg_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("个人中心");
        headImg_layout = (LinearLayout) findViewById(R.id.headImg_layout);
        headImg_layout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == headImg_layout){
            Intent intent = new Intent(mContext,UserInfoActivity.class);
            mContext.startActivity(intent);
        }

    }
}
