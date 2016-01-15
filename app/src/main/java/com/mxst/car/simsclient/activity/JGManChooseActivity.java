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
import com.mxst.car.simsclient.adapter.ChooseManAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.Brand;
import com.mxst.car.simsclient.entity.Sales;
import com.mxst.car.simsclient.entity.Stores;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class JGManChooseActivity extends CommonHeadPanelActivity {
    private List<Sales.SalesEntity> sales;
    private ChooseManAdapter adapter;
    private RecyclerView list;
    Stores.StoresEntity store;
    Brand.Brands brand;

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
        store = (Stores.StoresEntity) getIntent().getSerializableExtra("store");
        list = (RecyclerView) findViewById(R.id.man_recl);
        LinearLayout manLin = (LinearLayout) findViewById(R.id.jg_man);
        manLin.setVisibility(View.VISIBLE);
        ImageView img = (ImageView) findViewById(R.id.jg_man_store_img);
        BitmapUtils bitmapUtils = new BitmapUtils(this);
        bitmapUtils.display(img, brand.getLogoPath());
        TextView brandTv = (TextView) findViewById(R.id.jg_man_brand_tv);
        TextView storeTv = (TextView) findViewById(R.id.jg_man_store_tv);
        TextView addressTv = (TextView) findViewById(R.id.jg_man_address_tv);
        brandTv.setText(brand.getBrand());
        storeTv.setText(store.getName());
        addressTv.setText(store.getAddress());
        sales = new ArrayList<>();
        adapter = new ChooseManAdapter(this, sales);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new ChooseManAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent();
                i.putExtra("saleId", sales.get(position).getId() + "");
                i.putExtra("saleName", sales.get(position).getName());
                setResult(Constant.REQUESTCODE.CHOOSEMAN, i);
                finish();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JGManChooseActivity.this, JGDetailActivity.class).putExtra("id","1"));

            }
        });
    }

    private void getSales() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("store", store.getName());
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
        }.requestByPost(Constant.URL.JIGONG, params);

    }

}
