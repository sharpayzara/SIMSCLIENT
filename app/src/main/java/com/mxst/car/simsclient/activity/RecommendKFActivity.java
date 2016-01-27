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
import com.mxst.car.simsclient.adapter.RecommendKFAadapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.FindCusts;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/25.
 * version:  V1.0
 * Description:推荐客服列表
 */
public class RecommendKFActivity extends CommonHeadPanelActivity {

    private RecyclerView list;
    private List<FindCusts.CustsEntity> bean;
    private RecommendKFAadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_repair);
        super.onCreate(savedInstanceState);
        init();
        findCusts();
    }

    private void findCusts() {
        RequestParams params = new RequestParams();
        new BaseTask<JsonResult<FindCusts>, String>(this, "加载中") {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<FindCusts>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean.clear();
                    bean.addAll(result.getRecord().getCusts());
                } else {
                    Toast.makeText(RecommendKFActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.FINDCUSTS, params);

    }

    private void init() {
        showBackBtn();
        setHeadTitle("推荐客户列表");
        list = (RecyclerView) findViewById(R.id.fix_recl);
        bean = new ArrayList<>();
        adapter = new RecommendKFAadapter(this, bean);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new RecommendKFAadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(RecommendKFActivity.this, RecommendKFDeatilActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", bean.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
