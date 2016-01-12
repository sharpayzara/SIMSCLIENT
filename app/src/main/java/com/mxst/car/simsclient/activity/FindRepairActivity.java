package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.FindRepairAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.WxjcList;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/12.
 * version:  V1.0
 * Description:
 */
public class FindRepairActivity extends CommonHeadPanelActivity {
    private RecyclerView list;
    private List<WxjcList.WxjcListEntity> bean;
    private FindRepairAadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_repair);
        super.onCreate(savedInstanceState);
        init();
        getList();
    }

    private void getList() {
        RequestParams params = new RequestParams();
        //params.addQueryStringParameter("page", );
        new BaseTask<JsonResult<WxjcList>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<WxjcList>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean.clear();
                    bean.addAll(result.getRecord().getWxjcList());
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.GETLIST, params);


    }

    private void init() {
        list = (RecyclerView) findViewById(R.id.fix_recl);
        bean = new ArrayList<>();
        adapter = new FindRepairAadapter(this, bean);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }
}
