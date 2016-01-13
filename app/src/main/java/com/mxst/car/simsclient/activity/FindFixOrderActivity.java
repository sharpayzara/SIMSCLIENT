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
import com.mxst.car.simsclient.utils.Constant;

/**
 * Created by Joy on 2016/1/12 0012.
 */
public class FindFixOrderActivity extends CommonHeadPanelActivity {
    private TextView find_fix_num;
    private LinearLayout find_fix_project;
    private LinearLayout find_fix_cl;
    private String id;
    private RepairDetail bean;
    private TextView totalTv;
    private TextView jfTv;
    private TextView priceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fix_find);
        super.onCreate(savedInstanceState);
        initViews();
        getwxdorwxjc();
    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("查询维修单");
        id = getIntent().getStringExtra("id");
        find_fix_num = (TextView) findViewById(R.id.find_fix_num);
        find_fix_project = (LinearLayout) findViewById(R.id.find_fix_project);
        find_fix_cl = (LinearLayout) findViewById(R.id.find_fix_cl);
        totalTv = (TextView) findViewById(R.id.find_fix_total_tv);
        jfTv = (TextView) findViewById(R.id.find_fix_jf_tv);
        priceTv = (TextView) findViewById(R.id.find_fix_price_tv);
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
                    find_fix_num.setText(bean.getLoginNo());
                    priceTv.setText(bean.getFy().getYjcurr());
                    totalTv.setText(bean.getFy().getSjcurr());
                    jfTv.setText(bean.getFy().getJfcurr());
                    if (bean.getXm().size() != 0) {
                        for (int i = 0; i < bean.getXm().size(); i++) {
                            final TextView tv = new TextView(FindFixOrderActivity.this);
                            tv.setText(bean.getXm().get(i).getLx());
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            lp.setMargins(0, 20, 0, 0);
                            if (i == 0) {
                                find_fix_project.addView(tv);
                            } else {
                                find_fix_project.addView(tv, lp);
                            }

                        }
                    }
                    if (bean.getCl().size() != 0) {
                        LayoutInflater inflate = LayoutInflater.from(FindFixOrderActivity.this);

                        for (int i = 0; i < bean.getCl().size(); i++) {
                            View layout = inflate.inflate(R.layout.tab_find_fix, null);
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            lp.setMargins(0, 20, 0, 0);
                            TextView tv = (TextView) layout.findViewById(R.id.tab_fix_name);
                            TextView tv2 = (TextView) layout.findViewById(R.id.tab_fix_num);
                            tv.setText(bean.getCl().get(i).getLx());
                            tv2.setText(bean.getCl().get(i).getAmount());
                            if (i == 0) {
                                find_fix_cl.addView(layout);
                            } else {
                                find_fix_cl.addView(layout, lp);
                            }

                        }
                    }


                }

            }
        }.requestByPost(Constant.URL.GETWXDORWXJC, params);
    }

}
