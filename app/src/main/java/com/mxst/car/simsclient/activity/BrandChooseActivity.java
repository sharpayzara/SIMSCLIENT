package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.Brand;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/8.
 * version:  V1.0
 * Description:
 */
public class BrandChooseActivity extends CommonHeadPanelActivity implements View.OnClickListener {
    private ImageView brandImg1, brandImg2, brandImg3, brandImg4, brandImg5, brandImg6;
    private BitmapUtils utils;
    private List<Brand.Brands> bean = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_brand);
        super.onCreate(savedInstanceState);
        init();
        brands();
    }

    private void brands() {
        new BaseTask<JsonResult<Brand>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<Brand>() {
                };
            }

            @Override
            public void onSuccess() {
                bean = result.getRecord().getBrands();
                utils.display(brandImg1, bean.get(0).getLogoPath());
                brandImg1.setTag(bean.get(0).getBrand());
                brandImg2.setTag(bean.get(1).getBrand());
                brandImg3.setTag(bean.get(2).getBrand());
                brandImg4.setTag(bean.get(3).getBrand());
                brandImg5.setTag(bean.get(4).getBrand());
                utils.display(brandImg2, bean.get(1).getLogoPath());
                utils.display(brandImg3, bean.get(2).getLogoPath());
                utils.display(brandImg4, bean.get(3).getLogoPath());
                utils.display(brandImg5, bean.get(4).getLogoPath());

            }
        }.requestByPost(Constant.URL.BRAND, null);

    }

    private void init() {
        showBackBtn();
        setHeadTitle("选择品牌");
        utils = new BitmapUtils(this);
        brandImg1 = (ImageView) findViewById(R.id.brand_img1);
        brandImg2 = (ImageView) findViewById(R.id.brand_img2);
        brandImg3 = (ImageView) findViewById(R.id.brand_img3);
        brandImg4 = (ImageView) findViewById(R.id.brand_img4);
        brandImg5 = (ImageView) findViewById(R.id.brand_img5);
        brandImg6 = (ImageView) findViewById(R.id.brand_img6);
        brandImg1.setOnClickListener(this);
        brandImg2.setOnClickListener(this);
        brandImg3.setOnClickListener(this);
        brandImg4.setOnClickListener(this);
        brandImg5.setOnClickListener(this);
        brandImg6.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

    }
}
