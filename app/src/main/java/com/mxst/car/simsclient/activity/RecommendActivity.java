package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

/**
 * author   Joy
 * Date:  2016/1/8.
 * version:  V1.0
 * Description:马上推荐
 */
public class RecommendActivity extends CommonHeadPanelActivity implements View.OnClickListener {
    private EditText rec_name_et, rec_phone_et, rec_car_et;
    private TextView rec_brand_tv, rec_xiaoshou_tv, rec_shop_tv, rec_save_tv, rec_reset_tv;
    private String brand, store, num, saleId, saleName;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Constant.REQUESTCODE.CHOOSEBRAND:
                brand = data.getStringExtra("brand");
                rec_brand_tv.setText(brand);
                break;
            case Constant.REQUESTCODE.CHOOSESTORE:
                store = data.getStringExtra("store");
                num = data.getStringExtra("num");
                rec_shop_tv.setText(store);
                break;
            case Constant.REQUESTCODE.CHOOSEMAN:
                saleId = data.getStringExtra("saleId");
                saleName = data.getStringExtra("saleName");
                rec_xiaoshou_tv.setText(saleName);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        switch (tag) {
            case 1:  //选择品牌
                startActivityForResult(new Intent(this, BrandChooseActivity.class), 1);
                break;
            case 2: //选择销售
                if (!TextUtils.isEmpty(num)) {
                    Intent intent = new Intent(this, ManChooseActivity.class);
                    intent.putExtra("num", num);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(this, "请先选择门店", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3: //选择门店
                if (!TextUtils.isEmpty(brand)) {
                    Intent intent = new Intent(this, StoreChooseActivity.class);
                    intent.putExtra("brand", brand);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(this, "请先选择品牌", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (TextUtils.isEmpty(saleId) && TextUtils.isEmpty(num)) {
                    Toast.makeText(this, "请先完善信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                RequestParams params = new RequestParams();
                params.addQueryStringParameter("name", rec_name_et.getText().toString());
                params.addQueryStringParameter("phone", rec_phone_et.getText().toString());
                params.addQueryStringParameter("chexing", rec_car_et.getText().toString());
                params.addQueryStringParameter("brand", rec_brand_tv.getText().toString());
                params.addQueryStringParameter("store", num);
                params.addQueryStringParameter("saleId", saleId);
                new BaseTask<JsonResult<JSONObject>, String>(this, "加载中") {
                    @Override
                    public TypeToken setTypeToken() {
                        return new TypeToken<JSONObject>() {
                        };
                    }

                    @Override
                    public void onSuccess() {
                        if (result.isSuccess()) {
                            Toast.makeText(RecommendActivity.this, "推荐已提交!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RecommendActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.requestByPost(Constant.URL.RECCUST, params);
                break;
            case 5:
                rec_name_et.setText("");
                rec_phone_et.setText("");
                rec_car_et.setText("");
                rec_brand_tv.setText("");
                rec_xiaoshou_tv.setText("");
                rec_shop_tv.setText("");
                saleId = "";
                num = "";
                break;


        }

    }
}
