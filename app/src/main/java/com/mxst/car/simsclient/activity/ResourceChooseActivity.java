package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.ChooseAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.Filtrate;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/6.
 * version:  V1.0
 * Description:车型筛选
 */
public class ResourceChooseActivity extends CommonHeadPanelActivity {
    private TextView OkTv, choostTv;
    private ExpandableListView listView;
    private List<Filtrate> bean;
    private ChooseAdapter adapter;
    private String brand, spec, vehicleXinghao;
    private String mj, mjkx;
    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reschoose);
        super.onCreate(savedInstanceState);
        init();
        filtrate();
    }

    private void init() {
        brand = getIntent().getStringExtra("brand");
        spec = getIntent().getStringExtra("spec");
        vehicleXinghao = getIntent().getStringExtra("xingHao");
        showBackBtn();
        setHeadTitle(brand + vehicleXinghao);
        Button choose = (Button) findViewById(R.id.right_title);
        OkTv = (TextView) findViewById(R.id.choose_sure_tv);
        choostTv = (TextView) findViewById(R.id.choose_tv);
        listView = (ExpandableListView) findViewById(R.id.choose_res_lv);
        listView.setGroupIndicator(null);
        bean = new ArrayList<>();
        adapter = new ChooseAdapter(this, bean);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                v.setBackgroundColor(getResources().getColor(R.color.title_orange));
                mj = bean.get(groupPosition).getMj();
                mjkx = bean.get(groupPosition).getMjkxs().get(childPosition).getMjkx();
                choostTv.setText("(" + mjkx + ")");
                return true;
            }
        });
        choose.setVisibility(View.VISIBLE);
        choose.setBackground(null);
        choose.setText("关闭筛选");
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        OkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mj) && !TextUtils.isEmpty(mjkx)) {
                    Intent i = new Intent();
                    i.putExtra("mj", mj);
                    i.putExtra("mjkx", mjkx);
                    setResult(Constant.REQUESTCODE.CHOOSERES, i);
                }
                finish();
            }
        });
    }

    private void filtrate() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("brand", brand);
        params.addQueryStringParameter("spec", spec);
        params.addQueryStringParameter("vehicleXinghao", vehicleXinghao);
        new BaseTask<JsonResult<JSONObject>, String>(this, "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean.clear();
                    List<Filtrate> tempList = new Gson().fromJson(result.getRecord().optString("mjList"),
                            new TypeToken<List<Filtrate>>() {
                            }.getType());
                    Iterator<Filtrate> it = tempList.iterator();
                    while (it.hasNext()) {
                        bean.add(it.next());
                    }
                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                        total = total + adapter.getChildrenCount(i);
                        listView.expandGroup(i);
                    }
                    choostTv.setText("(" + total + "条车源)");
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ResourceChooseActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }.requestByPost(Constant.URL.FILTRATE, params);
    }
}
