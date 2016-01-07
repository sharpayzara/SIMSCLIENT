package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.ParaResult;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;

/**
 * Created by Joy on 2016/1/6 0006.
 */
public class CarDetailsActivity extends CommonHeadPanelActivity {
    private LinearLayout left_btn, car_detail_collect_lin, car_detail_share_lin, car_detail_more_lin;
    private TextView car_detail_color_tv, car_detail_engine_tv, car_detail_speed_tv, car_detail_site_tv,
            car_detail_price_tv, car_detail_time_tv, car_detail_name_tv, car_detail_guideprice_tv, car_detail_reserveprice_tv;
    private ImageView car_detail_img1, car_detail_img2, car_detail_img3, car_detail_img4, car_detail_img5;
    private String colorId;
    private ParaResult.ResourceDetail bean;
    private BitmapUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cardetail);
        super.onCreate(savedInstanceState);
        initViews();
        paraList();
    }

    private void paraList() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("colorId", colorId);
        new BaseTask<JsonResult<ParaResult>, String>(this, "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<ParaResult>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean = result.getRecord().getResourceDetail();
                    car_detail_color_tv.setText(bean.getOutColor());
                    car_detail_site_tv.setText(bean.getLocation());
                    car_detail_name_tv.setText(bean.getBrand() + " " + bean.getXinghao() + " " + bean.getNianKuan() + " " + bean.getCarType() + " " + bean.getKuanXing());
                    car_detail_price_tv.setText(bean.getTotalPrice() + "");
                    ArrayList<ImageView> imglist = new ArrayList<ImageView>();
                    imglist.add(car_detail_img1);
                    imglist.add(car_detail_img2);
                    imglist.add(car_detail_img3);
                    imglist.add(car_detail_img4);
                    imglist.add(car_detail_img5);

                    if (bean.getImgPaths().size() != 0 && imglist.size() >= bean.getImgPaths().size()) {
                        for (int i = 0; i < bean.getImgPaths().size(); i++) {
                            utils.display(imglist.get(i), bean.getImgPaths().get(i).getImgPath());
                        }
                    } else if (bean.getImgPaths().size() != 0 && imglist.size() < bean.getImgPaths().size()) {

                        for (int i = 0; i < imglist.size(); i++) {
                            utils.display(imglist.get(i), bean.getImgPaths().get(i).getImgPath());
                        }
                    }


                    if (bean.getGuidePrice() == 0) {
                        car_detail_guideprice_tv.setText("指导价:" + "暂无");
                    } else {
                        car_detail_guideprice_tv.setText("指导价:" + bean.getGuidePrice() + "");
                    }

                    for (int i = 0; i < bean.getParaList().size(); i++) {
                        if ("发动机".equals(bean.getParaList().get(i).getParaName())) {
                            car_detail_engine_tv.setText(bean.getParaList().get(i).getParaVal());
                        }
                        if ("变速箱".equals(bean.getParaList().get(i).getParaName())) {
                            car_detail_speed_tv.setText(bean.getParaList().get(i).getParaVal());
                        }
                    }

                }
            }

        }.requestByPost(Constant.URL.PARALIST, params);
    }

    private void initViews() {
        utils = new BitmapUtils(this);
        colorId = getIntent().getStringExtra("id");
        left_btn = (LinearLayout) findViewById(R.id.left_btn);
        car_detail_collect_lin = (LinearLayout) findViewById(R.id.car_detail_collect_lin);
        car_detail_share_lin = (LinearLayout) findViewById(R.id.car_detail_share_lin);
        car_detail_color_tv = (TextView) findViewById(R.id.car_detail_color_tv);
        car_detail_engine_tv = (TextView) findViewById(R.id.car_detail_engine_tv);
        car_detail_speed_tv = (TextView) findViewById(R.id.car_detail_speed_tv);
        car_detail_site_tv = (TextView) findViewById(R.id.car_detail_site_tv);
        car_detail_price_tv = (TextView) findViewById(R.id.car_detail_price_tv);
        car_detail_guideprice_tv = (TextView) findViewById(R.id.car_detail_guidePrice_tv);
        car_detail_reserveprice_tv = (TextView) findViewById(R.id.car_detail_reserveprice_tv);
        car_detail_price_tv = (TextView) findViewById(R.id.car_detail_price_tv);
        car_detail_time_tv = (TextView) findViewById(R.id.car_detail_time_tv);
        car_detail_name_tv = (TextView) findViewById(R.id.car_detail_name_tv);
        car_detail_img1 = (ImageView) findViewById(R.id.car_detail_img1);
        car_detail_img2 = (ImageView) findViewById(R.id.car_detail_img2);
        car_detail_img3 = (ImageView) findViewById(R.id.car_detail_img3);
        car_detail_img4 = (ImageView) findViewById(R.id.car_detail_img4);
        car_detail_img5 = (ImageView) findViewById(R.id.car_detail_img5);
        car_detail_more_lin = (LinearLayout) findViewById(R.id.car_detail_more_lin);
        car_detail_more_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarDetailsActivity.this, CarDetailsMoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ResourceDetail", bean);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
