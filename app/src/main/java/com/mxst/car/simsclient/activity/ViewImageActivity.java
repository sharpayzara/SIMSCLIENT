package com.mxst.car.simsclient.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.layout.HackyViewPager;

import uk.co.senab.photoview.PhotoView;

public class ViewImageActivity extends Activity {

    private Animator mCurrentAnimator;
    private int mShortAnimationDuration = 300;
    HackyViewPager viewPager;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        mContext  = this;
        initUI();
    }
    public int[] mThumbIds = { R.drawable.about, R.drawable.btn_home};
    private void initUI() {
        viewPager = (HackyViewPager) findViewById(R.id.expanded_image);
        viewPager.setAdapter(new SamplePagerAdapter(mThumbIds, mContext));
        viewPager.setCurrentItem(1);
    }
    class SamplePagerAdapter extends PagerAdapter {

        private int[] sDrawables;
        private Context mContext;

        public SamplePagerAdapter(int[] imgIds, Context context) {
            this.sDrawables = imgIds;
            this.mContext = context;
        }
        @Override
        public int getCount() {
            return sDrawables.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            final PhotoView photoView = new PhotoView(container.getContext());
            photoView.setImageResource(sDrawables[position]);
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
