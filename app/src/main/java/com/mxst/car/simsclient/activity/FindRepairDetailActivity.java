package com.mxst.car.simsclient.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

/**
 * author   Joy
 * Date:  2016/1/12.
 * version:  V1.0
 * Description:查询维修进程详情
 */
public class FindRepairDetailActivity extends CommonHeadPanelActivity {
    private TextView fix_detail_name;
    private TextView fix_detail_type;
    private TextView fix_detail_store;
    private TextView fix_detail_starttime;
    private TextView fix_detail_endtime;
    private TextView fix_detail_jiedai;
    private TextView hot;
    private TextView fix_detail_zhidan_time;
    private TextView fix_detail_showorder;
    private TextView hot1;
    private TextView fix_detail_kaigong_time;
    private TextView fix_detail_gongzhong;
    private TextView hot2;
    private TextView fix_detail_jungong_time;
    private TextView hot3;
    private TextView fix_detail_jiesuan_time;
    private TextView hot4;
    private TextView fix_detail_shoukuan_time;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fix_detail);
        super.onCreate(savedInstanceState);
        initViews();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initViews() {
        showBackBtn();
        setHeadTitle("查询维修进程");
        id = getIntent().getStringExtra("id");
        fix_detail_name = (TextView) findViewById(R.id.fix_detail_name);
        fix_detail_type = (TextView) findViewById(R.id.fix_detail_type);
        fix_detail_store = (TextView) findViewById(R.id.fix_detail_store);
        fix_detail_starttime = (TextView) findViewById(R.id.fix_detail_starttime);
        fix_detail_endtime = (TextView) findViewById(R.id.fix_detail_endtime);
        fix_detail_jiedai = (TextView) findViewById(R.id.fix_detail_jiedai);
        hot = (TextView) findViewById(R.id.hot);
        fix_detail_zhidan_time = (TextView) findViewById(R.id.fix_detail_zhidan_time);
        fix_detail_showorder = (TextView) findViewById(R.id.fix_detail_showorder);
        hot1 = (TextView) findViewById(R.id.hot1);
        fix_detail_kaigong_time = (TextView) findViewById(R.id.fix_detail_kaigong_time);
        fix_detail_gongzhong = (TextView) findViewById(R.id.fix_detail_gongzhong);
        hot2 = (TextView) findViewById(R.id.hot2);
        fix_detail_jungong_time = (TextView) findViewById(R.id.fix_detail_jungong_time);
        hot3 = (TextView) findViewById(R.id.hot3);
        fix_detail_jiesuan_time = (TextView) findViewById(R.id.fix_detail_jiesuan_time);
        hot4 = (TextView) findViewById(R.id.hot4);
        fix_detail_shoukuan_time = (TextView) findViewById(R.id.fix_detail_shoukuan_time);
        hot3.setBackground(getResources().getDrawable(R.drawable.bg_dot_true));
    }
}
