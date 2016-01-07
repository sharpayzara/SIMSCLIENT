package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.NewsMoreAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.NewsMoreList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class NewsMoreActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    RecyclerView mRecyclerView;
    NewsMoreAdapter mAdapter;
    Context mContext;
    RadioButton zx_radio,zy_radio;
    List<NewsMoreList.NewsMore> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_more);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        initData();
    }

    private void initData() {
        showBackBtn();
        setHeadTitle("资讯列表");
        list = new ArrayList<NewsMoreList.NewsMore>();
        mAdapter =new NewsMoreAdapter(mContext,list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadData();
    }
    private void loadData(){
        new BaseTask<JsonResult<NewsMoreList>,String>(mContext,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<NewsMoreList>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    list.clear();
                    List<NewsMoreList.NewsMore> tempList=  result.getRecord().getNewsList();
                    list.addAll(tempList);
                }
                mAdapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.NEWSLIST,new RequestParams());
    }

    private void initUI() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recy_view);
        zx_radio = (RadioButton) findViewById(R.id.zx_radio);
        zy_radio = (RadioButton) findViewById(R.id.zy_radio);
        zx_radio.setChecked(true);
    }

    @Override
    public void onClick(View v) {

    }
}

