package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.view.View;

import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

public class NewsMoreActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    String check = "((http|ftp|https)://)([a-zA-Z0-9_-]+\\.)*";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }
    private void initUI() {
        showBackBtn();
        setHeadTitle("资讯列表");
    }

    @Override
    public void onClick(View v) {

    }
}

