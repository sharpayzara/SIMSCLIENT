package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.layout.HackyViewPager;

import java.util.ArrayList;

import cn.jpush.android.api.InstrumentedActivity;
import uk.co.senab.photoview.PhotoView;

public class ViewImageURLActivity extends InstrumentedActivity {
    HackyViewPager viewPager;
    Context mContext;
    BitmapUtils utils;
    int currentPosition;
    ImageView tempIv;
    ArrayList<String> imageUrlList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        mContext  = this;
        utils = new BitmapUtils(mContext);
        imageUrlList = getIntent().getStringArrayListExtra("imgUrlList");
        currentPosition = getIntent().getIntExtra("position",0);
        utils.configDefaultLoadFailedImage(R.drawable.plugin_img);
        tempIv = new ImageView(mContext);
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
            return imageUrlList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            final PhotoView photoView = new PhotoView(container.getContext());
          //  photoView.setImageDrawable(new BitmapDrawable(imageList.get(position)));

            //photoView.setImageDrawable();
            utils.display(photoView,imageUrlList.get(position));
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
