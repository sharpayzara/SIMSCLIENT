package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.CarDetailAdapter;
import com.mxst.car.simsclient.entity.ParaResult;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/7.
 * version:  V1.0
 * Description:
 */
public class CarDetailsMoreActivity extends CommonHeadPanelActivity {
    private ListView listView;
    private List<ParaResult.ResourceDetail.ParaListEntity> paraList = new ArrayList<>();
    private CarDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_more);
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        showBackBtn();
        setHeadTitle("资源配置详情");
        listView = (ListView) findViewById(R.id.car_more_list);
        ParaResult.ResourceDetail bean = (ParaResult.ResourceDetail) getIntent().getSerializableExtra("ResourceDetail");
        paraList = bean.getParaList();

    }
}
