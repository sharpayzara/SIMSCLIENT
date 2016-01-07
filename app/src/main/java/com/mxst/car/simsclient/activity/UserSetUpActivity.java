package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

public class UserSetUpActivity extends CommonHeadPanelActivity {
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setup);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("个人设置");

    }

}
