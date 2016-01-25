package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

/**
 * author   Joy
 * Date:  2016/1/22.
 * version:  V1.0
 * Description:购物车详情
 */
public class FixDetailActivity extends CommonHeadPanelActivity {
    private TextView shop_address, shop_date, shop_pz, shop_type, shop_num, shop_jg, shop_jfye, shop_jfyeky, shop_djsye,shop_syjf_tv,shop_sjje;
    private EditText shop_sjje_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shop_fix_detail);
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("购物车");
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
    }
}
