package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.FixDetail;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/12.
 * version:  V1.0
 * Description:查询维修进程详情
 */
public class FindRepairDetailActivity extends CommonHeadPanelActivity {
    private TextView fix_detail_name, fix_detail_type, fix_detail_store, fix_detail_starttime, fix_detail_endtime, fix_detail_jiedai,
            call, hot, fix_detail_zhidan_time, fix_detail_showorder, hot1, fix_detail_kaigong_time, fix_detail_gongzhong, hot2, fix_detail_jungong_time, hot3, fix_detail_jiesuan_time, hot4, fix_detail_shoukuan_time;
    private String id;
    private FixDetail.DetailEntity bean;
    private List<TextView> hotlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fix_detail);
        super.onCreate(savedInstanceState);
        initViews();
        getDetail();
    }

    private void getDetail() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("id", id);
        new BaseTask<JsonResult<FixDetail>, String>(this) {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<FixDetail>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean = result.getRecord().getDetail();
                    fix_detail_name.setText(bean.getLicense());
                    fix_detail_type.setText(bean.getWxlx());
                    fix_detail_store.setText(bean.getFixName());
                    fix_detail_starttime.setText(bean.getNoteDate());
                    fix_detail_endtime.setText(bean.getOverDate());
                    fix_detail_jiedai.setText(bean.getHandmanName());
                    fix_detail_zhidan_time.setText(bean.getNoteDate());
                    //call tag
                    fix_detail_kaigong_time.setText(bean.getNoteDate());
                    fix_detail_gongzhong.setText(bean.getGz());
                    fix_detail_jungong_time.setText(bean.getJgDate());
                    fix_detail_jiesuan_time.setText(bean.getJsDate());
                    fix_detail_shoukuan_time.setText(bean.getSkdate());
                    int tag = Integer.parseInt(bean.getTag());
                    if (tag <= 2) {
                        hotlist.get(tag).setBackgroundResource(R.drawable.bg_dot_true);
                    } else if (tag == 6) {
                        hot4.setBackgroundResource(R.drawable.bg_dot_true);
                    } else {
                        hot3.setBackgroundResource(R.drawable.bg_dot_true);
                    }
                    call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getWxMobile()));
                            startActivity(i);

                        }
                    });
                    fix_detail_showorder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(FindRepairDetailActivity.this, FindFixOrderActivity.class).putExtra("id", bean.getId() + ""));

                        }
                    });
                }
            }
        }.requestByPost(Constant.URL.GETDETAIL, params);

    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("查询维修进程");
        id = getIntent().getStringExtra("id");
        call = (TextView) findViewById(R.id.fix_detail_call);
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
        hotlist.add(hot);
        hotlist.add(hot1);
        hotlist.add(hot2);
        hotlist.add(hot3);
        hotlist.add(hot4);

    }
}
