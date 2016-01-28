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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NewsFragment extends Fragment implements View.OnClickListener {
    LayoutInflater inflater;
    RelativeLayout adLayout,search_lly;
    Context mContext;
    LinearLayout adcolumnLayout,choose_llt;
    RelativeLayout dmf_layout;
    private TextView newsTitleTv, newsNewsTv, newsGuideTv, newsPriceTv, newsCultureTv,cancel_tv;
    private RecyclerView newsRecl;
    private View view;
    private String val;
    private List<IndexList>  bean;
    private NewsCentreAdapter adapter;
    private int llyHeight;
    private ImageView search_icon;
    private MaterialRefreshLayout materialRefreshLayout;
    private int currentPage = 1;
    ClearEditText search_et;
    ImageView pr_line,cc_line,ac_line,ap_line;
    private  String tempStr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        mContext = getActivity();
        this.inflater = inflater;
        llyHeight = (int) (getResources().getDisplayMetrics().density * 45 + 0.5);
        initUI();
        if(TextUtils.isEmpty(search_et.getText())){
            getNewsList("");
        }else{
            searchNewsList(search_et.getText().toString());
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        val = "";
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
        pr_line = (ImageView) view.findViewById(R.id.pr_line);
        cc_line = (ImageView) view.findViewById(R.id.cc_line);
        ac_line = (ImageView) view.findViewById(R.id.ac_line);
        ap_line = (ImageView) view.findViewById(R.id.ap_line);
        choose_llt = (LinearLayout) view.findViewById(R.id.choose_llt);
        if(bean == null){
            bean = new LinkedList<>();
        }
        if(adapter == null){
            adapter = new NewsCentreAdapter(getActivity(), bean);
        }

        newsRecl.setAdapter(adapter);
        newsRecl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        newsRecl.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.materialRefreshLayout);
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                currentPage = 1;
                bean.clear();
                if(TextUtils.isEmpty(search_et.getText().toString())){
                    getNewsList(val);
                }else{
                    searchNewsList(search_et.getText().toString());
                }
                materialRefreshLayout.setLoadMore(true);
            }
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                currentPage++;
                if(TextUtils.isEmpty(search_et.getText().toString())){
                    getNewsList(val);
                }else{
                    searchNewsList(search_et.getText().toString());
                }
            }
        });

        adapter.setOnItemClickListener(new NewsCentreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
                intent.putExtra("id", bean.get(position).getId() + "");
                intent.putExtra("content", bean.get(position).getSubtitle());
                intent.putExtra("title", bean.get(position).getTitle());
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
                bean.clear();
                currentPage = 1;
                searchNewsList(s.toString());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_news_tv:
                currentPage = 1;
                val = "PR";
                showCheckLine("pr");
                bean.clear();
                getNewsList(val);
                break;
            case R.id.news_guide_tv:
                val = "CC";
                showCheckLine("cc");
                currentPage = 1;
                bean.clear();
                getNewsList(val);
                break;
            case R.id.news_price_tv:
                val = "AC";
                showCheckLine("ac");
                currentPage = 1;
                bean.clear();
                getNewsList(val);
                break;
            case R.id.news_culture_tv:
                val = "AP";
                showCheckLine("ap");
                currentPage = 1;
                bean.clear();
                getNewsList(val);
                break;
            case R.id.search_icon:
                val = "";
                currentPage = 1;
                getNewsList("");
                doCloseLayout(search_lly);
                choose_llt.setVisibility(View.GONE);
                break;
            case R.id.cancel_tv:
                search_et.setText("");
                doOpenLayout(search_lly);
                choose_llt.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void getNewsList(String val) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("newsTypeVal", val);
         params.addQueryStringParameter("page",  currentPage+"");

        new BaseTask<JsonResult<JSONObject>, String>(getActivity(), "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    List<IndexList> tempList = new Gson().fromJson(result.getRecord().optString("newsList"),
                            new TypeToken<List<IndexList>>() {
                            }.getType());
                    if(tempList.size() == 0){
                        materialRefreshLayout.setLoadMore(false);
                        materialRefreshLayout.finishRefresh();
                        materialRefreshLayout.finishRefreshLoadMore();
                        return;
                    }
                    if(currentPage == 1){
                        bean.clear();
                    }
                    Iterator<IndexList> it = tempList.iterator();
                    while (it.hasNext()) {
                        bean.add(it.next());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                materialRefreshLayout.finishRefresh();
                materialRefreshLayout.finishRefreshLoadMore();
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
        showCheckLine("");
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("titleVal", val);
        params.addQueryStringParameter("page",  currentPage+"");

        new BaseTask<JsonResult<JSONObject>, String>(getActivity()) {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    List<IndexList> tempList = new Gson().fromJson(result.getRecord().optString("newsList"),
                            new TypeToken<List<IndexList>>() {
                            }.getType());
                    if(tempList.size() == 0){
                        materialRefreshLayout.setLoadMore(false);
                        materialRefreshLayout.finishRefresh();
                        materialRefreshLayout.finishRefreshLoadMore();
                        return;
                    }
                    if(currentPage == 1){
                        bean.clear();
                    }
                    Iterator<IndexList> it = tempList.iterator();
                    while (it.hasNext()) {
                        bean.add(it.next());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), result.getMsg(), Toast.LENGTH_SHORT).show();
                }
                materialRefreshLayout.finishRefresh();
                materialRefreshLayout.finishRefreshLoadMore();
            }
        }.requestByPost(Constant.URL.SEARCH_NEWS_LIST, params);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(!TextUtils.isEmpty(search_et.getText())){
            search_et.setText("");
            doOpenLayout(search_lly);
        }
    }
    public void showCheckLine(String val){
        pr_line.setVisibility(View.INVISIBLE);
        cc_line.setVisibility(View.INVISIBLE);
        ac_line.setVisibility(View.INVISIBLE);
        ap_line.setVisibility(View.INVISIBLE);
        switch (val){
            case "pr":
                pr_line.setVisibility(View.VISIBLE);
                break;
            case "cc":
                cc_line.setVisibility(View.VISIBLE);
                break;
            case "ac":
                ac_line.setVisibility(View.VISIBLE);
                break;
            case "ap":
                ap_line.setVisibility(View.VISIBLE);
                break;
        }
    }
}
