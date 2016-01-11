package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mxst.car.simsclient.utils.CommonUtil;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    private List<View> views = null;
    private List<String> urlList;
    Context mContext;

    public ImageAdapter(Context context,List<View> views,List<String> urlList) {
        this.views = views;
        mContext = context;
        this.urlList = urlList;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {

    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        CommonUtil.getBitMapUtils(mContext).display(views.get(arg1),urlList.get(arg1));
        return views.get(arg1);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }

}