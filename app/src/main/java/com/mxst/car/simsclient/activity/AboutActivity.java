package com.mxst.car.simsclient.activity;

import android.os.Bundle;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

/**
 * author   Joy
 * Date:  2016/3/15.
 * version:  V1.0
 * Description:个人 中心 /关于
 */
public class AboutActivity extends CommonHeadPanelActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
        super.onCreate(savedInstanceState);
        showBackBtn();
        setHeadTitle("关于");
    }
}
