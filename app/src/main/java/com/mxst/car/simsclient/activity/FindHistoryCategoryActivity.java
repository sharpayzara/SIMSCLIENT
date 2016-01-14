package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.FindWxAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.CarList;
import com.mxst.car.simsclient.entity.WxList;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/14.
 * version:  V1.0
 * Description:
 */
public class FindHistoryCategoryActivity extends CommonHeadPanelActivity {
    private TextView history_pp, history_cx, history_pz;
    private RecyclerView list;
    private List<WxList.WxlsListEntity> bean;
    private FindWxAadapter adapter;
    private String pz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_history);
        super.onCreate(savedInstanceState);
        initViews();
        getWxList();
    }

    private void initViews() {
        CarList.CarsEntity car = (CarList.CarsEntity) getIntent().getSerializableExtra("cars");
        history_pp = (TextView) findViewById(R.id.history_pp);
        history_cx = (TextView) findViewById(R.id.history_cx);
        history_pz = (TextView) findViewById(R.id.history_pz);
        list = (RecyclerView) findViewById(R.id.history_recl);
        pz = car.getLicense();
        history_pp.setText(car.getPp());
        history_pz.setText(car.getLicense());
        history_cx.setText(car.getCx());
        showBackBtn();
        setHeadTitle("维修历史");
        bean = new ArrayList<>();
        adapter = new FindWxAadapter(this, bean);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new FindWxAadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FindHistoryCategoryActivity.this, FindHistoryDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wxls", bean.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void getWxList() {
        RequestParams params = new RequestParams();
        //params.addQueryStringParameter("page", );
        params.addQueryStringParameter("license", pz);
        new BaseTask<JsonResult<WxList>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<WxList>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean.clear();
                    if (result.getRecord().getWxlsList().size() == 0) {
                        Toast.makeText(FindHistoryCategoryActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                    bean.addAll(result.getRecord().getWxlsList());
                } else {
                    Toast.makeText(FindHistoryCategoryActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.GETWXLIST, params);


    }
}
