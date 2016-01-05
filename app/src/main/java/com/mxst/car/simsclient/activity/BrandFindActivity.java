package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.SpecEAdapter;
import com.mxst.car.simsclient.entity.SpecList;

import java.util.ArrayList;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/5.
 * version:  V1.0
 * Description:
 */
public class BrandFindActivity extends CommonHeadPanelActivity {
    private String brand;
    private ExpandableListView listView;
    private List<SpecList> bean;
    //  private SpecAdapter adapter;
    private SpecEAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_brandfind);
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {
        brand = getIntent().getStringExtra("brand");
        listView = (ExpandableListView) findViewById(R.id.brandfind_list);
        listView.setGroupIndicator(null);
        adapter = new SpecEAdapter(this, bean);
        bean = new ArrayList<>();
        SpecList s = new SpecList();
        s.setSpec("中规");
        listView.setAdapter(adapter);
    }

}
