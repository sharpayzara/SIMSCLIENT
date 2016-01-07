package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

    public class GouCheActivity extends CommonHeadPanelActivity{
    Context mContext;

    LinearLayout headImg_layout,collect_lly,setup_lly,score_llt;
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

    }
}
