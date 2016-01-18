package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.JGCommentAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.JGDetail;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/15.
 * version:  V1.0
 * Description:技工详情
 */
public class JGDetailActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    private TextView jg_detail_name, jg_detail_phone, jg_detail_js, jg_detail_call;
    private RatingBar jg_detail_rating_rb;
    private ImageView jg_detail_head_img, jg_detail_zs_img, jg_detail_zs2_img, jg_detail_zs3_img;
    private List<JGDetail.CommentsEntity> bean = new ArrayList<>();
    private RecyclerView jg_detail_recl;
    private String id;
    private BitmapUtils utils;
    private List<ImageView> imglist;
    private JGCommentAadapter adapter;
    private Context mContext;
    int currentLicense = 0;
    ArrayList<Bitmap> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jg_detail);
        super.onCreate(savedInstanceState);
        mContext = this;
        tempList = new ArrayList();
        initViews();
        artisanDetail();
    }

    private void artisanDetail() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("id", id);
        new BaseTask<JsonResult<JGDetail>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JGDetail>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean.clear();
                    bean.addAll(result.getRecord().getComments());
                    utils.display(jg_detail_head_img, result.getRecord().getArtisanDetail().getHeadPortrait());
                    jg_detail_name.setText(result.getRecord().getArtisanDetail().getName());
                    jg_detail_phone.setText(result.getRecord().getArtisanDetail().getPhone());
                    jg_detail_rating_rb.setRating(result.getRecord().getArtisanDetail().getStar_level());
                    jg_detail_js.setText(result.getRecord().getArtisanDetail().getIntro());
                    currentLicense = result.getRecord().getZhengShu().size();
                    if (result.getRecord().getZhengShu().size() < 3) {
                        for (int i = 0; i < result.getRecord().getZhengShu().size(); i++) {
                            utils.display(imglist.get(i), result.getRecord().getZhengShu().get(i).getImg());
                        }
                    } else {
                        utils.display(imglist.get(0), result.getRecord().getZhengShu().get(0).getImg());
                        utils.display(imglist.get(1), result.getRecord().getZhengShu().get(1).getImg());
                        utils.display(imglist.get(2), result.getRecord().getZhengShu().get(2).getImg());
                    }

                    jg_detail_call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+result.getRecord().getArtisanDetail().getPhone()));
                            startActivity(intent);
                        }
                    });

                } else {
                    Toast.makeText(JGDetailActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.ARTISANDETAIL, params);
    }

    private void initViews() {
        showBackBtn();
        setHeadTitle("联系维修技工");
        id = getIntent().getStringExtra("saleId");
        utils = new BitmapUtils(this);
        jg_detail_head_img = (ImageView) findViewById(R.id.jg_detail_head_img);
        jg_detail_name = (TextView) findViewById(R.id.jg_detail_name);
        jg_detail_phone = (TextView) findViewById(R.id.jg_detail_phone);
        jg_detail_rating_rb = (RatingBar) findViewById(R.id.jg_detail_rating_rb);
        jg_detail_js = (TextView) findViewById(R.id.jg_detail_js);
        jg_detail_zs_img = (ImageView) findViewById(R.id.jg_detail_zs_img);
        jg_detail_zs2_img = (ImageView) findViewById(R.id.jg_detail_zs2_img);
        jg_detail_zs3_img = (ImageView) findViewById(R.id.jg_detail_zs3_img);
        jg_detail_zs_img.setDrawingCacheEnabled(true);
        jg_detail_zs2_img.setDrawingCacheEnabled(true);
        jg_detail_zs3_img.setDrawingCacheEnabled(true);
        jg_detail_recl = (RecyclerView) findViewById(R.id.jg_detail_recl);
        jg_detail_call = (TextView) findViewById(R.id.jg_detail_call);
        adapter = new JGCommentAadapter(this, bean);
        jg_detail_recl.setAdapter(adapter);
        jg_detail_recl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        jg_detail_recl.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        jg_detail_zs_img.setOnClickListener(this);
        jg_detail_zs2_img.setOnClickListener(this);
        jg_detail_zs3_img.setOnClickListener(this);
        imglist = new ArrayList<>();
        imglist.add(jg_detail_zs_img);
        imglist.add(jg_detail_zs2_img);
        imglist.add(jg_detail_zs3_img);
    }

    @Override
    public void onClick(View v) {
        tempList.clear();
        if(v == jg_detail_zs_img && currentLicense >= 1){
            for (int i = 0; i <currentLicense; i++) {
                tempList.add(imglist.get(i).getDrawingCache());
            }
            Intent intent = new Intent(mContext, ViewImageActivity.class);
            intent.putParcelableArrayListExtra("imgList", tempList);
            intent.putExtra("position",0);
            mContext.startActivity(intent);
        }else if(v == jg_detail_zs2_img && currentLicense >= 2){
            for (int i = 0; i <currentLicense; i++) {
                tempList.add(imglist.get(i).getDrawingCache());
            }
            Intent intent = new Intent(mContext, ViewImageActivity.class);
            intent.putParcelableArrayListExtra("imgList", tempList);
            intent.putExtra("position",1);
            mContext.startActivity(intent);
        }else if(v == jg_detail_zs3_img && currentLicense >= 3){
            for (int i = 0; i <currentLicense; i++) {
                tempList.add(imglist.get(i).getDrawingCache());
            }
            Intent intent = new Intent(mContext, ViewImageActivity.class);
            intent.putParcelableArrayListExtra("imgList", tempList);
            intent.putExtra("position",2);
            mContext.startActivity(intent);
        }
    }
}
