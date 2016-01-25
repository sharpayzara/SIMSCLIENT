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
import com.mxst.car.simsclient.adapter.TradeListAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.TradeList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class TradeListActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    Context mContext;
    private RecyclerView mRecyclerView;
    private List<TradeList.Trade> list;
    private TradeListAadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_trade);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        initData();
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("成交客户列表");
        mRecyclerView = (RecyclerView) findViewById(R.id.myCycle);
    }

    private void initData() {
        list = new ArrayList<>();
        adapter = new TradeListAadapter(this, list);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TradeListAadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext,TradeDetailActivity.class);
                intent.putExtra("trade",list.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadData();
    }

    private void loadData() {
        new BaseTask<JsonResult<TradeList>,String>(mContext,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<TradeList>(){};
            }

            @Override
            public void onSuccess() {
                list.clear();
                if(result.isSuccess()){
                    list.addAll(result.getRecord().getCusts());
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.TRADELIST,new RequestParams());
    }

    @Override
    public void onClick(View v) {


    }
}
