package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

public class EvaluateActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_evaluate);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
    }

    private void initUI() {
        setHeadTitle("技工评价");

    }


    @Override
    public void onClick(View v) {

    }
}
