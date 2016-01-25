package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.adapter.MarketPagerAdapter;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class  MarketFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private View root;
    LayoutInflater inflater;
    Context mContext;
    private MarketPagerAdapter adapter;
    List<Fragment> fragments;
    private Fragment businessFrag,goodsFrag;
    CheckBox mCheckbox;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(TextUtils.isEmpty(Constant.AUTHENTICATION_TOKEN)){
            root = inflater.inflate(R.layout.fragment_market_not_open, container, false);
            return root;
        }
        root = inflater.inflate(R.layout.fragment_market, container, false);
        mContext = getActivity();
        this.inflater = inflater;
        initUI();
        initData();
        return root;
    }

    private void initData() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new BusinessFragment());
        fragments.add(new GoodsFragment());
        adapter = new MarketPagerAdapter(getChildFragmentManager(), fragments);
        mCheckbox.setChecked(true);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(fragments.size() - 1);
        viewPager.setOnPageChangeListener(this);
        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewPager.setCurrentItem(0);
                }else{
                    viewPager.setCurrentItem(1);
                }
            }
        });
    }

    private void initUI() {
        viewPager = (ViewPager) root.findViewById(R.id.pager);
        mCheckbox = (CheckBox) root.findViewById(R.id.mCheckbox);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getTabState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void getTabState(int index) {
        if(index == 0){
            mCheckbox.setChecked(true);
        }else{
            mCheckbox.setChecked(false);
        }
    }
}
