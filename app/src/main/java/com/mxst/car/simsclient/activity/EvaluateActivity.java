package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RatingBar;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

public class EvaluateActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    Context mContext;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_evaluate);
        super.onCreate(savedInstanceState);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(4);
        mContext = this;
        initUI();
    }

    private void initUI() {
        setHeadTitle("技工评价");
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return false;
    }
}
