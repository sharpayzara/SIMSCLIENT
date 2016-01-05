package com.mxst.car.simsclient.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.adapter.SpecEAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.SpecList;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
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
    private SpecEAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_brandfind);
        super.onCreate(savedInstanceState);
        init();
        brandXinghaoList();
    }

    private void init() {
        brand = getIntent().getStringExtra("brand");
        showBackBtn();
        setHeadTitle(brand);
        listView = (ExpandableListView) findViewById(R.id.brandfind_list);
        listView.setGroupIndicator(null);
        bean = new ArrayList<>();
        adapter = new SpecEAdapter(this, bean);

        listView.setAdapter(adapter);

    }

    private void brandXinghaoList() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("brand", brand);
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
                    List<SpecList> tempList = new Gson().fromJson(result.getRecord().optString("specList"),
                            new TypeToken<List<SpecList>>() {
                            }.getType());
                    Iterator<SpecList> it = tempList.iterator();
                    while (it.hasNext()) {
                        bean.add(it.next());
                    }
                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                        listView.expandGroup(i);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(BrandFindActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }.requestByPost(Constant.URL.BRANDXINGHAOLIST, params);

    }

}
