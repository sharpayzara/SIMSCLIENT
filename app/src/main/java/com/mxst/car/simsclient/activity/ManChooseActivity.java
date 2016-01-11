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
import com.mxst.car.simsclient.adapter.ChooseManAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.Sales;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class ManChooseActivity extends CommonHeadPanelActivity {
    private List<Sales.SalesEntity> sales;
    private ChooseManAdapter adapter;
    private RecyclerView list;
    private String store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_man_chooose);
        super.onCreate(savedInstanceState);
        init();
        getSales();
    }

    private void init() {
        showBackBtn();
        setHeadTitle("选择销售");
        store = getIntent().getStringExtra("num");
        list = (RecyclerView) findViewById(R.id.man_recl);
        sales = new ArrayList<>();
        adapter = new ChooseManAdapter(this, sales);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new ChooseManAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent();
                i.putExtra("saleId", sales.get(position).getId()+"");
                i.putExtra("saleName", sales.get(position).getName());
                setResult(Constant.REQUESTCODE.CHOOSEMAN, i);
                finish();
            }
        });
    }

    private void getSales() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("store", store);
        new BaseTask<JsonResult<Sales>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<Sales>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    sales.clear();
                    sales.addAll(result.getRecord().getSales());
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.GETSALES, params);

    }

}
