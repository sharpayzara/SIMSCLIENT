package com.mxst.car.simsclient.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.mxst.car.simsclient.layout.ClearEditText;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.DividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsFragment extends Fragment implements View.OnClickListener {
    LayoutInflater inflater;
    RelativeLayout adLayout,search_lly;
    Context mContext;
    LinearLayout adcolumnLayout;
    RelativeLayout dmf_layout;
    private TextView newsTitleTv, newsNewsTv, newsGuideTv, newsPriceTv, newsCultureTv,cancel_tv;
    private RecyclerView newsRecl;
    private View view;
    private String val;
    private List<IndexList> bean;
    private NewsCentreAdapter adapter;
    private int llyHeight;
    private ImageView search_icon;
    ClearEditText search_et;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        mContext = getActivity();
        this.inflater = inflater;
        llyHeight = (int) (getResources().getDisplayMetrics().density * 45 + 0.5);
        initUI();
        getNewsList("");
        return view;
    }


    private void initUI() {
        search_et = (ClearEditText) view.findViewById(R.id.search_et);
        newsTitleTv = (TextView) view.findViewById(R.id.news_title_tv);
        newsNewsTv = (TextView) view.findViewById(R.id.news_news_tv);
        newsGuideTv = (TextView) view.findViewById(R.id.news_guide_tv);
        newsPriceTv = (TextView) view.findViewById(R.id.news_price_tv);
        newsCultureTv = (TextView) view.findViewById(R.id.news_culture_tv);
        newsRecl = (RecyclerView) view.findViewById(R.id.news_recl);
        search_icon = (ImageView) view.findViewById(R.id.search_icon);
        search_lly = (RelativeLayout) view.findViewById(R.id.search_lly);
        cancel_tv = (TextView) view.findViewById(R.id.cancel_tv);
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
        cancel_tv.setOnClickListener(this);
        search_icon.setOnClickListener(this);
        newsNewsTv.setOnClickListener(this);
        newsGuideTv.setOnClickListener(this);
        newsPriceTv.setOnClickListener(this);
        newsCultureTv.setOnClickListener(this);

        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchNewsList(s.toString());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_news_tv:
                val = "PR";
                getNewsList(val);
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
            case R.id.search_icon:
                doCloseLayout(search_lly);
                break;
            case R.id.cancel_tv:
                doOpenLayout(search_lly);
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


    private void doOpenLayout(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createAnimator(view, 0, llyHeight);
        animator.start();
    }

    private void doCloseLayout(final View view){
        ValueAnimator animator = createAnimator(view, view.getHeight(), 0);
        animator.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createAnimator(final View view,int start,int end){
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.setDuration(800).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int i = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams group = view.getLayoutParams();
                group.height = i;
                view.setLayoutParams(group);
            }
        });
        return animator;
    }

    private void searchNewsList(String val) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("titleVal", val);
        // params.addQueryStringParameter("page",  "");

        new BaseTask<JsonResult<JSONObject>, String>(getActivity()) {

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
        }.requestByPost(Constant.URL.SEARCH_NEWS_LIST, params);
    }

}
