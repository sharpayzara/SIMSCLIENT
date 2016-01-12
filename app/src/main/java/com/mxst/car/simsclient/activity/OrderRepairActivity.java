package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.OrderRepairListAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.OrderRepireList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class OrderRepairActivity extends CommonHeadPanelActivity{
    OrderRepairListAdapter mAdapter;
    Context mContext;
    RecyclerView myRecycle;
    List<OrderRepireList.OrderRepire> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order_repair);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        initData();
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("预约维修");
        myRecycle = (RecyclerView) findViewById(R.id.my_recycle);
    }

    private void initData() {
        list = new ArrayList<OrderRepireList.OrderRepire>();
        mAdapter = new OrderRepairListAdapter(mContext,list);
        myRecycle.setAdapter(mAdapter);
        myRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadData();
    }

    private void loadData() {
        new BaseTask<JsonResult<OrderRepireList>,String>(mContext,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<OrderRepireList>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    list.clear();
                    list.addAll(result.getRecord().getYyList());
                }
                mAdapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.ORDER_REPAIRE_LIST,new RequestParams());
    }
}
