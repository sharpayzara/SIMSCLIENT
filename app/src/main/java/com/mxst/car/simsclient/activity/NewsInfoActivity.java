package com.mxst.car.simsclient.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

/**
 * author   Joy
 * Date:  2016/1/4.
 * version:  V1.0
 * Description:
 */

public class NewsInfoActivity extends CommonHeadPanelActivity {
    private String id;
    private WebView mWebView;
    private Button collect;
    private boolean isCollect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_info);
        super.onCreate(savedInstanceState);
        init();
        if (!Constant.AUTHENTICATION_TOKEN.isEmpty()) {
            getCollectStatus();
        }

    }

    private void getCollectStatus() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("id", id);
        new BaseTask<JsonResult<JSONObject>, String>(this, "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    int flag = result.getRecord().optInt("flag");
                    if (flag == 1) {
                        isCollect = true;
                        collect.setBackgroundResource(R.drawable.btn_collect_true);
                    } else {
                        collect.setBackgroundResource(R.drawable.btn_collect);
                    }
                }
            }

        }.requestByPost(Constant.URL.GETCOLLECTSTATUS, params);
    }

    private void init() {
        id = getIntent().getStringExtra("id");
        mWebView = (WebView) findViewById(R.id.news_web);
        collect = (Button) findViewById(R.id.info_collect);
        mWebView.loadUrl("http://222.177.210.200/supplier/news/getNewsInfo?id=" + id);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.AUTHENTICATION_TOKEN.isEmpty()) {
                    Intent intent = new Intent(NewsInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                if (isCollect) {
                    collect.setBackgroundResource(R.drawable.btn_collect);
                } else {
                    collect.setBackgroundResource(R.drawable.btn_collect_true);
                }
                collect();

            }
        });
    }

    private void collect() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("type", "1");
        params.addQueryStringParameter("id", id);
        new BaseTask<JsonResult<JSONObject>, String>(this, "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    Toast.makeText(NewsInfoActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

        }.requestByPost(Constant.URL.COLLECT, params);

    }

}
