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
import com.mxst.car.simsclient.config.CommonBean;
import com.mxst.car.simsclient.config.ScoreDetailConfig;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        showBackBtn();
        setHeadTitle("积分详细");
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
                    JSONObject jsonObj = result.getRecord();
                    String resultStr = jsonObj.toString().substring(1, jsonObj.toString().length()-1);
                   resultStr =  resultStr.substring(resultStr.indexOf('{')) ;
                    try {
                        jsonObj = new JSONObject(resultStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return ;
                   }
                 //   JSONObject entryObj = jsonObj.optJSONObject("tjkh");
                    Map<String, List<CommonBean>> map = ScoreDetailConfig.configMap;
                    Iterator<Map.Entry<String, List<CommonBean>>> entries = map.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry<String, List<CommonBean>> entry = entries.next();
                        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                        if(entry.getKey().equals(jsonObj.optString("jfxw"))){
                            for(CommonBean bean : entry.getValue()){
                                View view = inflater.inflate(R.layout.item_score_detail,null);
                                TextView name_tv = (TextView) view.findViewById(R.id.name_tv);
                                TextView value_tv = (TextView) view.findViewById(R.id.value_tv);
                                name_tv.setText(bean.getFieldValue());
                                value_tv.setText(jsonObj.optString(bean.getFieldName()));
                                score_llt.addView(view);
                            }
                            return;
                        }
                    }
                }
            }
        }.requestByPost(Constant.URL.JFDETAIL,params);
    }

}