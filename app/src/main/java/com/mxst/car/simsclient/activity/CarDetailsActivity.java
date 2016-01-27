package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.ParaList;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy on 2016/1/6 0006.
 */
public class CarDetailsActivity extends CommonHeadPanelActivity implements OnClickListener {
    private LinearLayout left_btn, car_detail_collect_lin, car_detail_share_lin, car_detail_more_lin;
    private TextView car_detail_color_tv, car_detail_engine_tv, car_detail_speed_tv, car_detail_site_tv, car_detail_tjjf, car_detail_gcjf,
            car_detail_price_tv, car_detail_time_tv, car_detail_name_tv, car_detail_guideprice_tv, car_detail_reserveprice_tv;
    private ImageView car_detail_img1, car_detail_img2, car_detail_img3, car_detail_img4, car_detail_img5;
    private String colorId, id;
    private ParaList.ResourceDetailEntity bean;
    private Boolean isCollect = false;
    private Button collectBtn;
    private List<ParaList.ConfigInfoEntity> configinfoList = new ArrayList<>();
    private BitmapUtils utils;
    private LinearLayout imgLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cardetail);
        super.onCreate(savedInstanceState);
        initViews();
        paraList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        paraList();
    }

    private void paraList() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("colorId", colorId);
        if (!TextUtils.isEmpty(Constant.AUTHENTICATION_TOKEN)) {
            params.addQueryStringParameter("flg", "1");
        }
        new BaseTask<JsonResult<ParaList>, String>(this, "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<ParaList>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess() && result.getRecord() != null) {
                    configinfoList = result.getRecord().getConfigInfo();
                    bean = result.getRecord().getResourceDetail();
                    id = bean.getId() + "";
                    if (result.getRecord().getFlag() == 1) {
                        isCollect = true;
                        collectBtn.setBackgroundResource(R.drawable.btn_collect_true);
                    } else {
                        isCollect = false;
                        collectBtn.setBackgroundResource(R.drawable.btn_collect);
                    }
                    car_detail_tjjf.setText(bean.getCjjf() + "");
                    car_detail_gcjf.setText(bean.getGcjf() + "");
                    car_detail_color_tv.setText(bean.getOutColor());
                    car_detail_site_tv.setText(bean.getLocation());
                    car_detail_name_tv.setText(bean.getBrand() + " " + bean.getXinghao() + " " + bean.getNianKuan() + " " + bean.getCarType() + " " + bean.getKuanXing());
                    car_detail_price_tv.setText(bean.getTotalPrice() + "");
                    car_detail_time_tv.setText(bean.getCommit_date());
                    car_detail_reserveprice_tv.setText(bean.getDingjin());
                    car_detail_engine_tv.setText(bean.getFdj());
                    car_detail_speed_tv.setText(bean.getBsx());
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
                    } else if (bean.getImgPaths().size() == 0) {
                        imgLin.setVisibility(View.GONE);
                    }


                    if (bean.getGuidePrice() == 0) {
                        car_detail_guideprice_tv.setText("指导价:" + "暂无");
                    } else {
                        car_detail_guideprice_tv.setText("指导价:" + bean.getGuidePrice() + "");
                    }

                }
            }

        }.requestByPost(Constant.URL.PARALIST, params);
    }

    private void initViews() {
        utils = new BitmapUtils(this);
        colorId = getIntent().getStringExtra("id");
        collectBtn = (Button) findViewById(R.id.car_detail_collect_cb);
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
        car_detail_tjjf = (TextView) findViewById(R.id.car_detail_tjjf_tv);
        car_detail_gcjf = (TextView) findViewById(R.id.car_detail_gcjf_tv);
        imgLin = (LinearLayout) findViewById(R.id.car_detail_img_lin);
        car_detail_img1.setOnClickListener(this);
        car_detail_img2.setOnClickListener(this);
        car_detail_img3.setOnClickListener(this);
        car_detail_img4.setOnClickListener(this);
        car_detail_img5.setOnClickListener(this);
        car_detail_share_lin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        car_detail_more_lin = (LinearLayout) findViewById(R.id.car_detail_more_lin);
        car_detail_more_lin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (configinfoList.size() == 0) {
                    Toast.makeText(CarDetailsActivity.this, "暂无更多详情", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent(CarDetailsActivity.this, CarDetailsMoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("configinfoList", (Serializable) configinfoList);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        collectBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.AUTHENTICATION_TOKEN.isEmpty()) {
                    Intent intent = new Intent(CarDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                collect();

            }
        });

    }

    private void collect() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("type", "0");
        params.addQueryStringParameter("id", colorId);
        new BaseTask<JsonResult<JSONObject>, String>(this, "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    if (isCollect) {
                        collectBtn.setBackgroundResource(R.drawable.btn_collect);
                        isCollect = false;
                    } else {
                        collectBtn.setBackgroundResource(R.drawable.btn_collect_true);
                        isCollect = true;
                    }
                    Toast.makeText(CarDetailsActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

        }.requestByPost(Constant.URL.COLLECT, params);


    }

    @Override
    public void onClick(View v) {
        if (v == car_detail_img1 || v == car_detail_img2 || v == car_detail_img3
                || v == car_detail_img4 || v == car_detail_img5) {
            Intent intent = new Intent(this, ViewImageURLActivity.class);
            ArrayList<String> list = new ArrayList<String>();
            for (ParaList.ResourceDetailEntity.ImgPathsEntity entity : bean.getImgPaths()) {
                list.add(entity.getImgPath());
            }
            if (v == car_detail_img1) {
                intent.putExtra("position", 0);
            } else if (v == car_detail_img2) {
                intent.putExtra("position", 1);
            } else if (v == car_detail_img3) {
                intent.putExtra("position", 2);
            } else if (v == car_detail_img4) {
                intent.putExtra("position", 3);
            } else if (v == car_detail_img5) {
                intent.putExtra("position", 4);
            }
            intent.putStringArrayListExtra("imgUrlList", list);
            startActivity(intent);
        }

    }
}
