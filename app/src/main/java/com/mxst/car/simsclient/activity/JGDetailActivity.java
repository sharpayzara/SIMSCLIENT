package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.JGCommentAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.JGDetail;
import com.mxst.car.simsclient.utils.CommonUtil;
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
public class JGDetailActivity extends CommonHeadPanelActivity implements View.OnClickListener {
    private TextView jg_detail_name, jg_detail_phone, jg_detail_js, jg_detail_call;
    private RatingBar jg_detail_rating_rb;
    private ImageView jg_detail_head_img, jg_detail_zs_img, jg_detail_zs2_img, jg_detail_zs3_img, jg_detail_zs5_img, jg_detail_zs4_img;
    private List<JGDetail.CommentsEntity> bean = new ArrayList<>();
    private RecyclerView jg_detail_recl;
    private String id;
    private BitmapUtils utils;
    private List<ImageView> imglist;
    private JGCommentAadapter adapter;
    private Context mContext;
    private MaterialRefreshLayout materialRefreshLayout;

    ArrayList<String> imgurlList;
    private int currentPage = 1;
    private LinearLayout ImgLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jg_detail);
        super.onCreate(savedInstanceState);
        mContext = this;
        imgurlList = new ArrayList();
        initViews();
        artisanDetail();
    }

    private void artisanDetail() {     //// TODO: 2016/2/1 Page
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("id", id);
        params.addQueryStringParameter("page", currentPage + "");
        new BaseTask<JsonResult<JGDetail>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JGDetail>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    if (result.getRecord().getComments().size() == 0) {
                        materialRefreshLayout.setLoadMore(false);
                        materialRefreshLayout.finishRefresh();
                        materialRefreshLayout.finishRefreshLoadMore();
                    }
                    bean.addAll(result.getRecord().getComments());
                    utils.display(jg_detail_head_img, result.getRecord().getArtisanDetail().getHeadPortrait());
                    jg_detail_name.setText(result.getRecord().getArtisanDetail().getName());
                    jg_detail_phone.setText(result.getRecord().getArtisanDetail().getPhone());
                    jg_detail_rating_rb.setRating(CommonUtil.switchRatingValue(result.getRecord().getArtisanDetail().getStar_level()));
                    jg_detail_js.setText(result.getRecord().getArtisanDetail().getIntro());
                    if (result.getRecord().getZhengShu().size() == 0) {
                        ImgLin.setVisibility(View.GONE);
                    }
                    if (result.getRecord().getZhengShu().size() < 5) {
                        for (int i = 0; i < result.getRecord().getZhengShu().size(); i++) {
                            imgurlList.add(result.getRecord().getZhengShu().get(i).getImg());
                            utils.display(imglist.get(i), result.getRecord().getZhengShu().get(i).getImg());
                        }
                    } else {
                        for (int i = 0; i < result.getRecord().getZhengShu().size(); i++) {
                            imgurlList.add(result.getRecord().getZhengShu().get(i).getImg());
                        }
                        utils.display(imglist.get(0), result.getRecord().getZhengShu().get(0).getImg());
                        utils.display(imglist.get(1), result.getRecord().getZhengShu().get(1).getImg());
                        utils.display(imglist.get(2), result.getRecord().getZhengShu().get(2).getImg());
                        utils.display(imglist.get(3), result.getRecord().getZhengShu().get(3).getImg());
                        utils.display(imglist.get(4), result.getRecord().getZhengShu().get(4).getImg());
                    }

                    jg_detail_call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + result.getRecord().getArtisanDetail().getPhone()));
                            startActivity(intent);
                        }
                    });

                } else {
                    Toast.makeText(JGDetailActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                materialRefreshLayout.finishRefresh();
                materialRefreshLayout.finishRefreshLoadMore();
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
        jg_detail_zs4_img = (ImageView) findViewById(R.id.jg_detail_zs4_img);
        jg_detail_zs5_img = (ImageView) findViewById(R.id.jg_detail_zs5_img);
        ImgLin = (LinearLayout) findViewById(R.id.jg_detail_img_lin);
        jg_detail_zs_img.setDrawingCacheEnabled(true);
        jg_detail_zs2_img.setDrawingCacheEnabled(true);
        jg_detail_zs3_img.setDrawingCacheEnabled(true);
        jg_detail_zs4_img.setDrawingCacheEnabled(true);
        jg_detail_zs5_img.setDrawingCacheEnabled(true);
        jg_detail_recl = (RecyclerView) findViewById(R.id.jg_detail_recl);
        jg_detail_call = (TextView) findViewById(R.id.jg_detail_call);
        adapter = new JGCommentAadapter(this, bean);
        jg_detail_recl.setAdapter(adapter);
        jg_detail_recl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        jg_detail_recl.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        jg_detail_zs_img.setOnClickListener(this);
        jg_detail_zs2_img.setOnClickListener(this);
        jg_detail_zs4_img.setOnClickListener(this);
        jg_detail_zs5_img.setOnClickListener(this);
        jg_detail_zs3_img.setOnClickListener(this);
        imglist = new ArrayList<>();
        imglist.add(jg_detail_zs_img);
        imglist.add(jg_detail_zs2_img);
        imglist.add(jg_detail_zs3_img);
        imglist.add(jg_detail_zs4_img);
        imglist.add(jg_detail_zs5_img);


        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.materialRefreshLayout);
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                currentPage = 1;
                bean.clear();
                artisanDetail();
                materialRefreshLayout.setLoadMore(true);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                currentPage++;
                artisanDetail();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int size = imgurlList.size();
        Intent intent = new Intent(mContext, ViewImageURLActivity.class);
        if (v == jg_detail_zs_img && size >= 1) {
            intent.putExtra("position", 0);
        } else if (v == jg_detail_zs2_img && size >= 2) {
            intent.putExtra("position", 1);
        } else if (v == jg_detail_zs3_img && size >= 3) {
            intent.putExtra("position", 2);
        }else if(v==jg_detail_zs4_img&&size>=4){
            intent.putExtra("position", 3);
        }else if(v==jg_detail_zs5_img&&size>=5){
            intent.putExtra("position", 4);
        }
        intent.putStringArrayListExtra("imgUrlList", imgurlList);
        startActivity(intent);
    }
}
