package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.ResourceAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.BrandResource;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/6.
 * version:  V1.0
 * Description:车型列表
 */
public class ResourceFindActivity extends CommonHeadPanelActivity {

    private TextView res_mj_tv, res_clear_tv, res_kx_tv;
    private RecyclerView res_rcl;
    private List<BrandResource> bean = new ArrayList<>();
    private ResourceAdapter adapter;
    private String brand, xingHao, spec, mj, kx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_resourcefind);
        super.onCreate(savedInstanceState);
        initViews();
        brandResourceList();
    }

    private void initViews() {
        brand = getIntent().getStringExtra("brand");
        xingHao = getIntent().getStringExtra("xingHao");
        showBackBtn();
        setHeadTitle(xingHao);
        xingHao = xingHao.substring(brand.length(), xingHao.length());
        spec = getIntent().getStringExtra("spec");
        Button choose = (Button) findViewById(R.id.right_title);
        res_kx_tv = (TextView) findViewById(R.id.res_kx_tv);
        res_clear_tv = (TextView) findViewById(R.id.res_clear_tv);
        res_rcl = (RecyclerView) findViewById(R.id.res_rcl);
        adapter = new ResourceAdapter(this, bean);
        res_rcl.setAdapter(adapter);
        res_rcl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        res_rcl.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new ResourceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ResourceFindActivity.this, CarDetailsActivity.class);
                intent.putExtra("id", bean.get(position).getColorId() + "");
                startActivity(intent);
            }
        });
        choose.setVisibility(View.VISIBLE);
        choose.setBackground(null);
        choose.setText("筛选");
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResourceFindActivity.this, ResourceChooseActivity.class);
                intent.putExtra("brand", brand);
                intent.putExtra("spec", spec);
                intent.putExtra("xingHao", xingHao);
                startActivityForResult(intent, 1);
            }
        });
        res_clear_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res_kx_tv.setText("");
                mj = "";
                kx = "";
                brandResourceList();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.REQUESTCODE.CHOOSERES) {
            findViewById(R.id.res_lin).setVisibility(View.VISIBLE);
            mj = data.getStringExtra("mj");
            kx = data.getStringExtra("mjkx");
            res_kx_tv.setText(kx);
            String[] mykx = kx.split(" ");
            kx = mykx[mykx.length - 1];
            //  res_mj_tv.setText(mj);
            brandResourceList();
        }
    }

    private void brandResourceList() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("brand", brand);
        params.addQueryStringParameter("xingHao", xingHao);
        params.addQueryStringParameter("spec", spec);
        if (!TextUtils.isEmpty(mj) || !TextUtils.isEmpty(kx)) {
            params.addQueryStringParameter("mj", mj);
            params.addQueryStringParameter("kx", kx);
        }
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
                    List<BrandResource> tempList = new Gson().fromJson(result.getRecord().optString("resourceList"),
                            new TypeToken<List<BrandResource>>() {
                            }.getType());
                    Iterator<BrandResource> it = tempList.iterator();
                    while (it.hasNext()) {
                        bean.add(it.next());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ResourceFindActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }.requestByPost(Constant.URL.BRANDRESOURCELIST, params);

    }
}
