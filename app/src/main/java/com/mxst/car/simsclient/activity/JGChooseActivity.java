package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.ChooseJGAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.JGList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class JGChooseActivity extends CommonHeadPanelActivity {
    private List<JGList.JG> list;
    private ChooseJGAdapter adapter;
    private RecyclerView myRecycle;
    private String store, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jg_chooose);
        super.onCreate(savedInstanceState);
        init();
        getJGMans();
    }

    private void init() {
        showBackBtn();
        setHeadTitle("选择技工");
        brand = getIntent().getStringExtra("brand");
        store = getIntent().getStringExtra("store");
        myRecycle = (RecyclerView) findViewById(R.id.my_recycle);
        list = new ArrayList<>();
        adapter = new ChooseJGAdapter(this, list);
        myRecycle.setAdapter(adapter);
        myRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setOnItemClickListener(new ChooseJGAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent();
                i.putExtra("jgName", list.get(position).getName());
                i.putExtra("jgNum", list.get(position).getNum());
                setResult(Constant.REQUESTCODE.CHOOSEJG, i);
                finish();
            }
        });

    }

    private void getJGMans() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("brand", brand);
        params.addQueryStringParameter("store", store);
        new BaseTask<JsonResult<JGList>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JGList>() {};
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    list.clear();
                    list.addAll(result.getRecord().getJigongs());
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.GETJG, params);
    }
}
