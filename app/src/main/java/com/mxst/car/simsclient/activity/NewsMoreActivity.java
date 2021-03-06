package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.CollectZXAdapter;
import com.mxst.car.simsclient.adapter.CollectZYAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.NewsMoreList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class NewsMoreActivity extends CommonHeadPanelActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    RecyclerView mRecyclerViewZX,mRecyclerViewZY;
    CollectZXAdapter mAdapterZX;
    CollectZYAdapter mAdapterZY;
    Context mContext;
    RadioButton zx_radio,zy_radio;
    List<NewsMoreList.NewsMore> list;
    RadioGroup radioGroup;
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
       // mAdapterZX = new CollectZXAdapter(mContext,list);
        mRecyclerViewZX.setAdapter(mAdapterZX);
        mRecyclerViewZX.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //mAdapterZY = new CollectZYAdapter(mContext,list);
        mRecyclerViewZY.setAdapter(mAdapterZY);
        mRecyclerViewZY.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
                mAdapterZX.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.NEWZYLIST,new RequestParams());

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
                mAdapterZX.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.NEWZXLIST,new RequestParams());
    }

    private void initUI() {
        mRecyclerViewZX = (RecyclerView) findViewById(R.id.recy_view_zx);
        mRecyclerViewZY = (RecyclerView) findViewById(R.id.recy_view_zy);
        zx_radio = (RadioButton) findViewById(R.id.zx_radio);
        zy_radio = (RadioButton) findViewById(R.id.zy_radio);
        zx_radio.setChecked(true);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.zx_radio:
                mRecyclerViewZX.setVisibility(View.VISIBLE);
                mRecyclerViewZY.setVisibility(View.GONE);
                break;
            case R.id.zy_radio:
                mRecyclerViewZX.setVisibility(View.GONE);
                mRecyclerViewZY.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}

