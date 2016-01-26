package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

public class ScoreDetailActivity extends CommonHeadPanelActivity{
    Context mContext;
    LinearLayout score_llt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_score_detail);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }


    private void initUI() {
        score_llt = (LinearLayout) findViewById(R.id.score_llt);
    }
    private void initData() {
        loadData();
    }

    private void loadData() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("id",getIntent().getStringExtra("id"));
        new BaseTask<JsonResult<JSONObject>,String>(mContext,R.string.download_notice){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    LayoutInflater inflater = LayoutInflater.from(mContext);
                    View view = inflater.inflate(R.layout.item_score_detail,null);
                    TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
                    TextView value_tv = (TextView) view.findViewById(R.id.value_tv);
                    score_llt.addView(view);
                }
            }
        }.requestByPost(Constant.URL.JFDETAIL,params);
    }

}