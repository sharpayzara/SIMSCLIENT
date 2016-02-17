package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.InsureDetailAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.InsureDetailList;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class InsureDetailActivity extends CommonHeadPanelActivity{
    Context mContext;
    List<InsureDetailList.InsureDetail>list;
    InsureDetailAdapter mAdapter;
    TextView license,pp,brandNo;
    String vinNo;
    RecyclerView my_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_insure_detail);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        initData();
    }
    private void initUI() {
        showBackBtn();
        setHeadTitle("保险查询");
        my_recycle = (RecyclerView) findViewById(R.id.my_recycle);
        license = (TextView) findViewById(R.id.license);
        pp = (TextView) findViewById(R.id.pp);
        brandNo = (TextView) findViewById(R.id.brandNo);
    }

    private void initData() {
        vinNo = getIntent().getStringExtra("vinNo");
        list = new ArrayList<>();
        mAdapter = new InsureDetailAdapter(mContext,list);
        my_recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        my_recycle.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new InsureDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext,InsureItemDetailActivity.class);
                intent.putExtra("brandNo",brandNo.getText().toString());
                intent.putExtra("pp",pp.getText().toString());
                intent.putExtra("license",license.getText().toString());
                intent.putExtra("sxbDate",list.get(position).getSxeDate());
                intent.putExtra("noteNo",list.get(position).getNoteNo());
                intent.putExtra("name",list.get(position).getName());
                startActivity(intent);
            }
        });
        license.setText(getIntent().getStringExtra("license"));
        pp.setText(getIntent().getStringExtra("pp"));
        brandNo.setText(getIntent().getStringExtra("brandNo"));
        loadData();
    }

    private void loadData() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("vinNo",vinNo);
        new BaseTask<JsonResult<InsureDetailList>,String>(mContext,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<InsureDetailList>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    list.clear();
                    list.addAll(result.getRecord().getBxList());
                }else{
                    CommonUtil.showToastToShort(mContext,result.getMsg());
                }
                mAdapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.INSUREdETAIL,params);
    }


}
