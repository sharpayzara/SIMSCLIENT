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
import com.mxst.car.simsclient.adapter.FindHistoryAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.CarList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/14.
 * version:  V1.0
 * Description:维修历史
 */
public class FindHistoryActivity extends CommonHeadPanelActivity {
    private RecyclerView list;
    private List<CarList.CarsEntity> bean;
    private FindHistoryAadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_repair);
        super.onCreate(savedInstanceState);
        init();
        getCarList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
      //  getCarList();
    }

    private void init() {
        showBackBtn();
        setHeadTitle("维修历史");
        list = (RecyclerView) findViewById(R.id.fix_recl);
        bean = new ArrayList<>();
        adapter = new FindHistoryAadapter(this, bean);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //   list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new FindHistoryAadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FindHistoryActivity.this, FindHistoryCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cars", bean.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getCarList() {
        RequestParams params = new RequestParams();
        //params.addQueryStringParameter("page", );
        new BaseTask<JsonResult<CarList>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<CarList>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean.clear();
                    if (result.getRecord().getCars().size() == 0) {
                        Toast.makeText(FindHistoryActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                    bean.addAll(result.getRecord().getCars());
                } else {
                    Toast.makeText(FindHistoryActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.GETCARLIST, params);
    }
}
