package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        //adcolumnLayout = (LinearLayout) root.findViewById(R.id.ad_column);
        mContext = getActivity();
        this.inflater = inflater;
        //	initUI();
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
        rightIcon.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }
}
