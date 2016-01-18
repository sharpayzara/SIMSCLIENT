package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.OrderRepairListAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.OrderRepireList;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class OrderRepairActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    OrderRepairListAdapter mAdapter;
    Context mContext;
    RecyclerView myRecycle;
    List<OrderRepireList.OrderRepire> list;
    ImageButton addNewBtn;
    int currentPage = 1;

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
        addNewBtn = (ImageButton) findViewById(R.id.add_new_btn);
        addNewBtn.setOnClickListener(this);
    }

    private void initData() {
        list = new ArrayList<OrderRepireList.OrderRepire>();
        mAdapter = new OrderRepairListAdapter(mContext,list);
        myRecycle.setAdapter(mAdapter);
        myRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadData(1);
    }

    private void loadData(int currentPage) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("page",currentPage+"");
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
                }else{
                    CommonUtil.showToastToShort(mContext,result.getMsg());
                }
                mAdapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.ORDER_REPAIRE_LIST,params);
    }

    @Override
    public void onClick(View v) {
        if(v == addNewBtn){
            Intent intent = new Intent(this,AddOrderRepairActivity.class);
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 12){
            loadData(1);
        }
    }
}
