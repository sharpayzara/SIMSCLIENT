package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.NewsInfoActivity;
import com.mxst.car.simsclient.adapter.NewsCentreAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.IndexList;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsFragment extends Fragment implements View.OnClickListener {
    LayoutInflater inflater;
    RelativeLayout adLayout;
    Context mContext;
    LinearLayout adcolumnLayout;
    RelativeLayout dmf_layout;
    private TextView newsTitleTv, newsNewsTv, newsGuideTv, newsPriceTv, newsCultureTv;
    private RecyclerView newsRecl;
    private ImageView rightIcon;
    private View view;
    private String val;
    private List<IndexList> bean;
    private NewsCentreAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        //adcolumnLayout = (LinearLayout) root.findViewById(R.id.ad_column);
        mContext = getActivity();
        this.inflater = inflater;
        initUI();
        getNewsList("");
        return view;
    }


    private void initUI() {
        newsTitleTv = (TextView) view.findViewById(R.id.news_title_tv);
        rightIcon = (ImageView) view.findViewById(R.id.right_icon);
        newsNewsTv = (TextView) view.findViewById(R.id.news_news_tv);
        newsGuideTv = (TextView) view.findViewById(R.id.news_guide_tv);
        newsPriceTv = (TextView) view.findViewById(R.id.news_price_tv);
        newsCultureTv = (TextView) view.findViewById(R.id.news_culture_tv);
        newsRecl = (RecyclerView) view.findViewById(R.id.news_recl);

        bean = new ArrayList<>();
        adapter = new NewsCentreAdapter(getActivity(), bean);
        newsRecl.setAdapter(adapter);
        newsRecl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        newsRecl.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new NewsCentreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
                intent.putExtra("id", bean.get(position).getId() + "");
                startActivity(intent);
            }
        });


        rightIcon.setOnClickListener(this);
        newsNewsTv.setOnClickListener(this);
        newsGuideTv.setOnClickListener(this);
        newsPriceTv.setOnClickListener(this);
        newsCultureTv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_news_tv:
                val = "PR";
                getNewsList(val);
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
                intent.putExtra("id", "10");
                startActivity(intent);
                break;
            case R.id.news_guide_tv:
                val = "CC";
                getNewsList(val);
                break;
            case R.id.news_price_tv:
                val = "AC";
                getNewsList(val);
                break;
            case R.id.news_culture_tv:
                val = "AP";
                getNewsList(val);
                break;
        }
    }

    private void getNewsList(String val) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("newsTypeVal", val);
        // params.addQueryStringParameter("page",  "");

        new BaseTask<JsonResult<JSONObject>, String>(getActivity(), "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    bean.clear();
                    List<IndexList> tempList = new Gson().fromJson(result.getRecord().optString("newsList"),
                            new TypeToken<List<IndexList>>() {
                            }.getType());
                    Iterator<IndexList> it = tempList.iterator();
                    while (it.hasNext()) {
                        bean.add(it.next());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }.requestByPost(Constant.URL.GET_NEWS_LIST, params);
    }

}
