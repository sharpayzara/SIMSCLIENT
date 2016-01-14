package com.mxst.car.simsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.ChooseStoreAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.Brand;
import com.mxst.car.simsclient.entity.Stores;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class JGStoreChooseActivity extends CommonHeadPanelActivity {
    private List<Stores.StoresEntity> stores;
    private ChooseStoreAdapter adapter;
    private RecyclerView list;
    private String store;
    Brand.Brands brand;
    private LinearLayout store_lin;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_man_chooose_jg);
        super.onCreate(savedInstanceState);
        init();
        getSales();
    }

    private void init() {
        showBackBtn();
        setHeadTitle("联系维修技工");
        brand = (Brand.Brands) getIntent().getSerializableExtra("brand");
        list = (RecyclerView) findViewById(R.id.man_recl);
        store_lin = (LinearLayout) findViewById(R.id.jg_store);
        store_lin.setVisibility(View.VISIBLE);
        img = (ImageView) findViewById(R.id.jg_store_img);
        BitmapUtils bitmapUtils = new BitmapUtils(this);
        bitmapUtils.display(img, brand.getLogoPath());
        TextView tv = (TextView) findViewById(R.id.jg_store_tv);
        tv.setText(brand.getBrand());
        stores = new ArrayList<>();
        adapter = new ChooseStoreAdapter(this, stores);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new ChooseStoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(JGStoreChooseActivity.this, JGManChooseActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("brand", brand);
                Stores.StoresEntity store = stores.get(position);
                b.putSerializable("brand", brand);
                b.putSerializable("store", store);
                i.putExtras(b);
                startActivity(i);

            }
        });

    }

    private void getSales() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("brand", brand.getBrand());
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
