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
import com.mxst.car.simsclient.entity.CollectZXList;
import com.mxst.car.simsclient.entity.CollectZYList;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends CommonHeadPanelActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    RecyclerView mRecyclerViewZX,mRecyclerViewZY;
    CollectZXAdapter mAdapterZX;
    CollectZYAdapter mAdapterZY;
    Context mContext;
    RadioButton zx_radio,zy_radio;
    List<CollectZXList.ZXEntity> list;
    List<CollectZYList.ZYEntity> list2;
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
        list = new ArrayList<CollectZXList.ZXEntity>();
        list2 = new ArrayList<CollectZYList.ZYEntity>();
        mAdapterZX = new CollectZXAdapter(mContext,list);
        mRecyclerViewZX.setAdapter(mAdapterZX);
        mRecyclerViewZX.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapterZY = new CollectZYAdapter(mContext,list2);
        mRecyclerViewZY.setAdapter(mAdapterZY);
        mRecyclerViewZY.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        loadData();
    }
    private void loadData(){
        new BaseTask<JsonResult<CollectZXList>,String>(mContext,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<CollectZXList>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    list.clear();
                    List<CollectZXList.ZXEntity> tempList=  result.getRecord().getNewsList();
                    list.addAll(tempList);
                }
                mAdapterZX.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.NEWZXLIST,new RequestParams());

        new BaseTask<JsonResult<CollectZYList>,String>(mContext){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<CollectZYList>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    list2.clear();
                    List<CollectZYList.ZYEntity> tempList=  result.getRecord().getResources();
                    if(tempList != null){
                        list2.addAll(tempList);
                    }else{
                        CommonUtil.showToastToShort(mContext,"数为据空");
                    }
                }
                mAdapterZX.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.NEWZYLIST,new RequestParams());
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

