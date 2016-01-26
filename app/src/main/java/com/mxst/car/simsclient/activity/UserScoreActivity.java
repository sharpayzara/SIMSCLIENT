package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.ScorelistAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.ScoreList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class UserScoreActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    ScorelistAdapter mAdapter;
    RecyclerView myRecycle;
    Context mContext;
    List<ScoreList.Score> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_score_list);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        initData();
    }


    private void initData() {
        list = new ArrayList<ScoreList.Score>();
        mAdapter = new ScorelistAdapter(mContext,list);
        mAdapter.setOnItemClickListener(new ScorelistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext,ScoreDetailActivity.class);
                intent.putExtra("id",list.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        myRecycle.setAdapter(mAdapter);
        myRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadData();
    }

    private void loadData() {
        new BaseTask<JsonResult<ScoreList>,String>(mContext,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<ScoreList>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    list.clear();
                    list.addAll(result.getRecord().getJfList());
                }
                mAdapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.SCORELIST,new RequestParams());
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("个人中心");
        myRecycle = (RecyclerView) findViewById(R.id.my_recycle);
    }

    @Override
    public void onClick(View v) {

    }
}

