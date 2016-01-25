package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.entity.FindCusts;

/**
 * author   Joy
 * Date:  2016/1/25.
 * version:  V1.0
 * Description:推荐客户详情
 */
public class RecommendKFDeatilActivity extends CommonHeadPanelActivity {
    private TextView com_detail_name, com_detail_phone, com_detail_type, com_detail_date, com_detail_brand, com_detail_store, com_detail_car;
    private FindCusts.CustsEntity bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_com_detail);
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("推荐客户详情");
        bean = (FindCusts.CustsEntity) getIntent().getSerializableExtra("bean");
        com_detail_name = (TextView) findViewById(R.id.com_detail_name);
        com_detail_phone = (TextView) findViewById(R.id.com_detail_phone);
        com_detail_type = (TextView) findViewById(R.id.com_detail_type);
        com_detail_date = (TextView) findViewById(R.id.com_detail_date);
        com_detail_brand = (TextView) findViewById(R.id.com_detail_brand);
        com_detail_store = (TextView) findViewById(R.id.com_detail_store);
        com_detail_car = (TextView) findViewById(R.id.com_detail_car);

        com_detail_name.setText(bean.getName());
        com_detail_phone.setText(bean.getPhone());
        com_detail_type.setText(bean.getEffective());
        com_detail_date.setText(bean.getTjDate());
        com_detail_brand.setText(bean.getTjbrand());
        com_detail_car.setText(bean.getTjchexing());
        com_detail_store.setText(bean.getStore());
    }
}
