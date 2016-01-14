package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.RepairDetail;
import com.mxst.car.simsclient.entity.WxList;
import com.mxst.car.simsclient.utils.Constant;

/**
 * author   Joy
 * Date:  2016/1/14.
 * version:  V1.0
 * Description: 维修历史详情
 */
public class FindHistoryDetailActivity extends CommonHeadPanelActivity {
    private TextView wx_pz, wx_wxlx, wx_gls, wx_pp, wx_cx, wx_num, wx_dat;
    private LinearLayout wx_project, wx_cl, wx_price;
    private TextView wx_price_tv;
    private TextView wx_jf_tv;
    private TextView wx_total_tv;
    private String id;
    private RepairDetail bean;

    @Override
    protected void onCreate(Bundle savedInstancetate) {
        setContentView(R.layout.activity_wx_detail);
        super.onCreate(savedInstancetate);
        initViews();
        getwxdorwxjc();
    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("维修历史");
        wx_pz = (TextView) findViewById(R.id.wx_pz);
        wx_wxlx = (TextView) findViewById(R.id.wx_wxlx);
        wx_gls = (TextView) findViewById(R.id.wx_gls);
        wx_pp = (TextView) findViewById(R.id.wx_pp);
        wx_cx = (TextView) findViewById(R.id.wx_cx);
        wx_num = (TextView) findViewById(R.id.wx_num);
        wx_dat = (TextView) findViewById(R.id.wx_dat);
        wx_project = (LinearLayout) findViewById(R.id.wx_project);
        wx_cl = (LinearLayout) findViewById(R.id.wx_cl);
        wx_price_tv = (TextView) findViewById(R.id.wx_price_tv);
        wx_jf_tv = (TextView) findViewById(R.id.wx_jf_tv);
        wx_total_tv = (TextView) findViewById(R.id.wx_total_tv);
        wx_price = (LinearLayout) findViewById(R.id.wx_price);
        WxList.WxlsListEntity wx = (WxList.WxlsListEntity) getIntent().getSerializableExtra("wxls");
        id = wx.getId();
    }

    private void getwxdorwxjc() {
        final RequestParams params = new RequestParams();
        params.addQueryStringParameter("id", id);
        new BaseTask<JsonResult<RepairDetail>, String>(this) {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<RepairDetail>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean = result.getRecord();
                    wx_pz.setText(bean.getLicense());
                    wx_wxlx.setText(bean.getWxlx());
                    wx_gls.setText(bean.getGls());
                    wx_dat.setText(bean.getNotedate());
                    wx_num.setText(bean.getLoginNo());
                    wx_pp.setText(bean.getPp());
                    wx_cx.setText(bean.getCx());
                    wx_price_tv.setText(bean.getFy().getYjcurr());
                    wx_total_tv.setText(bean.getFy().getSjcurr());
                    wx_jf_tv.setText(bean.getFy().getJfcurr());
                    if (bean.getXm().size() != 0) {
                        for (int i = 0; i < bean.getXm().size(); i++) {
                            final TextView tv = new TextView(FindHistoryDetailActivity.this);
                            tv.setText(bean.getXm().get(i).getLx());
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            lp.setMargins(0, 20, 0, 0);
                            if (i == 0) {
                                wx_project.addView(tv);
                            } else {
                                wx_project.addView(tv, lp);
                            }

                        }
                    }
                    if (bean.getCl().size() != 0) {
                        LayoutInflater inflate = LayoutInflater.from(FindHistoryDetailActivity.this);

                        for (int i = 0; i < bean.getCl().size(); i++) {
                            View layout = inflate.inflate(R.layout.tab_find_fix, null);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            lp.setMargins(0, 20, 0, 0);
                            TextView tv = (TextView) layout.findViewById(R.id.tab_fix_name);
                            TextView tv2 = (TextView) layout.findViewById(R.id.tab_fix_num);
                            tv.setText(bean.getCl().get(i).getLx());
                            tv2.setText(bean.getCl().get(i).getAmount());
                            if (i == 0) {
                                wx_cl.addView(layout);
                            } else {
                                wx_cl.addView(layout, lp);
                            }

                        }
                    }


                }

            }
        }.requestByPost(Constant.URL.GETWXDORWXJC, params);
    }
}
