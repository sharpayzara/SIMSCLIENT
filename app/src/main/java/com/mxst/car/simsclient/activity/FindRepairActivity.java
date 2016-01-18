package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.FindRepairAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.WxjcList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/12.
 * version:  V1.0
 * Description:查询维修进程
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
                } else {
                    Toast.makeText(FindRepairActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.GETLIST, params);


    }

    private void init() {
        showBackBtn();
        setHeadTitle("查询维修进程");
        list = (RecyclerView) findViewById(R.id.fix_recl);
        bean = new ArrayList<>();
        adapter = new FindRepairAadapter(this, bean);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new FindRepairAadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(FindRepairActivity.this, FindRepairDetailActivity.class).putExtra("id", bean.get(position).getId()+""));
            }
        });
    }
}
