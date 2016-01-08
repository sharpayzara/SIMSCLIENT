package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

/**
 * author   Joy
 * Date:  2016/1/8.
 * version:  V1.0
 * Description:马上推荐
 */
public class RecommendActivity extends CommonHeadPanelActivity implements View.OnClickListener {
    private EditText rec_name_et, rec_phone_et, rec_car_et;
    private TextView rec_brand_tv, rec_xiaoshou_tv, rec_shop_tv, rec_save_tv, rec_reset_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recommend);
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("推荐页面");
        rec_name_et = (EditText) findViewById(R.id.rec_name_et);
        rec_phone_et = (EditText) findViewById(R.id.rec_phone_et);
        rec_car_et = (EditText) findViewById(R.id.rec_car_et);
        rec_brand_tv = (TextView) findViewById(R.id.rec_brand_tv);
        rec_xiaoshou_tv = (TextView) findViewById(R.id.rec_xiaoshou_tv);
        rec_shop_tv = (TextView) findViewById(R.id.rec_shop_tv);
        rec_save_tv = (TextView) findViewById(R.id.rec_save_tv);
        rec_reset_tv = (TextView) findViewById(R.id.rec_reset_tv);
        rec_brand_tv.setOnClickListener(this);
        rec_xiaoshou_tv.setOnClickListener(this);
        rec_shop_tv.setOnClickListener(this);
        rec_save_tv.setOnClickListener(this);
        rec_reset_tv.setOnClickListener(this);
        rec_brand_tv.setTag(1);
        rec_xiaoshou_tv.setTag(2);
        rec_shop_tv.setTag(3);
        rec_save_tv.setTag(4);
        rec_reset_tv.setTag(5);


    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        switch (tag) {
            case 1:  //选择品牌
            startActivityForResult(new Intent(this,BrandChooseActivity.class),1);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;


        }

    }
}
