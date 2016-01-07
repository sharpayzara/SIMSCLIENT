package com.mxst.car.simsclient.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.layout.HackyViewPager;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

public class ViewImageActivity extends Activity {
    HackyViewPager viewPager;
    Context mContext;
    ArrayList<Bitmap> imageList;
    int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        mContext  = this;
        imageList = getIntent().getParcelableArrayListExtra("imgList");
        currentPosition = getIntent().getIntExtra("position",0);
        initUI();
    }
    private void initUI() {
        viewPager = (HackyViewPager) findViewById(R.id.expanded_image);
        viewPager.setAdapter(new SamplePagerAdapter());
        viewPager.setCurrentItem(currentPosition );
    }
    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            final PhotoView photoView = new PhotoView(container.getContext());
            photoView.setImageDrawable(new BitmapDrawable(imageList.get(position)));
            container.addView(photoView, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.activity_old_out_in,0);
    }
}
