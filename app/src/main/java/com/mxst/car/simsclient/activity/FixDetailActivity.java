package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.WxDetail;
import com.mxst.car.simsclient.utils.Constant;

/**
 * author   Joy
 * Date:  2016/1/22.
 * version:  V1.0
 * Description:购物车详情
 */
public class FixDetailActivity extends CommonHeadPanelActivity {
    private TextView shop_address, shop_date, shop_pz, shop_type, shop_num, shop_jg, shop_jfye, shop_jfyeky, shop_djsye, shop_syjf_tv, shop_sjje;
    private EditText shop_sjje_et;
    private WxDetail.DetailEntity bean;
    private String num;
    private TextView ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shop_fix_detail);
        super.onCreate(savedInstanceState);
        initViews();
        wxDetail();
    }

    private void wxDetail() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("loginNo", num);
        new BaseTask<JsonResult<WxDetail>, String>(this) {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<WxDetail>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean = result.getRecord().getDetail();
                    shop_address.setText(bean.getFixName());
                    shop_date.setText(bean.getNoteDate());
                    shop_pz.setText(bean.getLicense());
                    shop_num.setText(num);
                    shop_type.setText(bean.getWxlx());
                    shop_jg.setText("维修技工:" + bean.getHandmanName());
                    shop_jfye.setText(bean.getCurJf() + "");
                    shop_jfyeky.setText("(可用:" + bean.getKyjf() + ")");
                    shop_djsye.setText("￥" + bean.getYjcurr());
                    shop_sjje_et.setText("0");
                    //    Double total = bean.getCurJf() * bean.getJfPrice();
                    //  String result = String.format("%.2f", total);
                    shop_syjf_tv.setText("￥0");
                    shop_sjje.setText("￥" + bean.getYjcurr());
                }
            }
        }.requestByPost(Constant.URL.WXDETAIL, params);
    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("购物车");
        num = getIntent().getStringExtra("loginNo");
        shop_address = (TextView) findViewById(R.id.shop_address);
        shop_date = (TextView) findViewById(R.id.shop_date);
        shop_pz = (TextView) findViewById(R.id.shop_pz);
        shop_type = (TextView) findViewById(R.id.shop_type);
        shop_num = (TextView) findViewById(R.id.shop_num);
        shop_jg = (TextView) findViewById(R.id.shop_jg);
        shop_jfye = (TextView) findViewById(R.id.shop_jfye);
        shop_jfyeky = (TextView) findViewById(R.id.shop_jfyeky);
        shop_djsye = (TextView) findViewById(R.id.shop_djsye);
        shop_sjje_et = (EditText) findViewById(R.id.shop_sjje_et);
        shop_syjf_tv = (TextView) findViewById(R.id.shop_syjf_tv);
        shop_sjje = (TextView) findViewById(R.id.shop_sjje);
        ok = (TextView) findViewById(R.id.shop_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.addBodyParameter("loginNo", num);
                params.addBodyParameter("useJf", shop_sjje_et.getText().toString());

                new BaseTask<JsonResult<String>, String>(FixDetailActivity.this) {
                    @Override
                    public TypeToken setTypeToken() {
                        return new TypeToken<String>() {
                        };
                    }

                    @Override
                    public void onSuccess() {
                        if (result.isSuccess()) {
                            Toast.makeText(FixDetailActivity.this, "提交成功!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(FixDetailActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.requestByPost(Constant.URL.DIKOU, params);
            }
        });


        shop_sjje_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!shop_sjje_et.getText().toString().isEmpty()) {
                    int ii = Integer.parseInt(shop_sjje_et.getText().toString());
                    if (ii > bean.getCurJf()) {
                        Toast.makeText(FixDetailActivity.this, "已输出的积分超过使用范围", Toast.LENGTH_SHORT).show();
                        shop_sjje_et.setText(bean.getKyjf() + "");
                        return;
                    }
                    if (0 != ii) {
                        Double total = ii * bean.getJfPrice();
                        String r = String.format("%.2f", total);
                        shop_syjf_tv.setText("-￥" + r);
                        Double djjje = Double.valueOf(bean.getYjcurr() - total);
                        String result = String.format("%.2f", djjje);
                        shop_sjje.setText("￥" + result);
                    }
                } else {
                    shop_syjf_tv.setText("-￥0");
                    shop_sjje.setText("￥" + bean.getYjcurr());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
