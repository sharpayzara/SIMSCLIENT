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
import com.mxst.car.simsclient.adapter.ChooseStoreAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.Stores;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class StoreChooseActivity extends CommonHeadPanelActivity {
    private List<Stores.StoresEntity> stores;
    private ChooseStoreAdapter adapter;
    private RecyclerView list;
    private String store, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_man_chooose);
        super.onCreate(savedInstanceState);
        init();
        getSales();
    }

    private void init() {
        showBackBtn();
        setHeadTitle("选择门店");
        brand = getIntent().getStringExtra("brand");
        list = (RecyclerView) findViewById(R.id.man_recl);
        stores = new ArrayList<>();
        adapter = new ChooseStoreAdapter(this, stores);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new ChooseStoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent();
                i.putExtra("store", stores.get(position).getName());
                i.putExtra("num", stores.get(position).getNum());
                setResult(Constant.REQUESTCODE.CHOOSESTORE, i);
                finish();
            }
        });

    }

    private void getSales() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("brand", brand);
        new BaseTask<JsonResult<Stores>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<Stores>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    stores.clear();
                    stores.addAll(result.getRecord().getStores());
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.STORE, params);

    }


}
