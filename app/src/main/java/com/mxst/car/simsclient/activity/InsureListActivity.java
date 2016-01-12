package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.InsureListAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.InsureList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class InsureListActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    InsureListAdapter mAdapter;
    RecyclerView myRecycle;
    List<InsureList.Insure> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_insure_list);
        super.onCreate(savedInstanceState);
        initUI();
        initData();
    }

    private void initUI(){
        myRecycle = (RecyclerView) findViewById(R.id.my_recycle);
    }
    private void initData() {
        showBackBtn();
        setHeadTitle("保险查询");
        list = new ArrayList<InsureList.Insure>();
        mAdapter = new InsureListAdapter(this,list);
        myRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myRecycle.setAdapter(mAdapter);
        loadData();
    }

    private void loadData() {
        new BaseTask<JsonResult<InsureList>,String>(this,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<InsureList>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    list.clear();
                    if(result.getRecord().getCars().size() > 0 ){
                        list.addAll(result.getRecord().getCars());
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.INSURANCE_CAR_LIST,new RequestParams());
    }

    @Override
    public void onClick(View v) {

    }
}
