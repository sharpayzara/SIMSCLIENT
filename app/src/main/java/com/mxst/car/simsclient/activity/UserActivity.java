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

    LinearLayout headImg_layout,collect_lly,setup_lly,score_llt,account_llt;
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
        collect_lly = (LinearLayout) findViewById(R.id.collect_lly);
        collect_lly.setOnClickListener(this);
        setup_lly = (LinearLayout) findViewById(R.id.setup_lly);
        setup_lly.setOnClickListener(this);
        score_llt = (LinearLayout) findViewById(R.id.score_llt);
        score_llt.setOnClickListener(this);
        account_llt = (LinearLayout) findViewById(R.id.account_llt);
        account_llt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == headImg_layout || v == account_llt){
            Intent intent = new Intent(mContext,UserInfoActivity.class);
            mContext.startActivity(intent);
        }else if(v == collect_lly){
            Intent intent = new Intent(mContext, CollectActivity.class);
            mContext.startActivity(intent);
        }else if(v == setup_lly){
            Intent intent = new Intent(mContext, UserSetUpActivity.class);
            mContext.startActivity(intent);
        }else if(v == score_llt){

        }
    }
}
