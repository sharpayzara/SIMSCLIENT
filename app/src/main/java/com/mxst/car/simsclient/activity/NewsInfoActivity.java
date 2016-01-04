package com.mxst.car.simsclient.activity;


import android.os.Bundle;
import android.webkit.WebView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;

/**
 * author   Joy
 * Date:  2016/1/4.
 * version:  V1.0
 * Description:
 */

public class NewsInfoActivity extends CommonHeadPanelActivity {
    private String id;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_info);
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        showBackBtn();
        setHeadTitle("资讯详情");
        id = getIntent().getStringExtra("id");
        mWebView = (WebView) findViewById(R.id.news_web);
        mWebView.loadUrl("http://222.177.210.200/supplier/news/getNewsInfo?id=" + id);
    }
}
