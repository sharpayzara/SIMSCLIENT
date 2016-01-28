package com.mxst.car.simsclient.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.utils.Constant;

public class ImageLogoText extends LinearLayout {
	private Context mContext = null;
	private ImageView mImageView = null;
	private TextView mTextView = null;
	private final static int DEFAULT_IMAGE_WIDTH = 29;
	private final static int DEFAULT_IMAGE_HEIGHT = 29;
	private final int CHECKED_COLOR = Color.rgb(255,127,0); //选中橘红色
	private final int UNCHECKED_COLOR = Color.GRAY;   //自然灰色

	public ImageLogoText(Context context) {
		super(context);
		mContext = context;
	}


	public ImageLogoText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View parentView = inflater.inflate(R.layout.image_text_layout, this, true);
		mImageView = (ImageView) parentView.findViewById(R.id.image_iamge_text);
		mTextView = (TextView) parentView.findViewById(R.id.text_iamge_text);
	}

	public void setImage(int id) {
		if (mImageView != null) {
			mImageView.setImageResource(id);
			setImageSize(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
		}
	}

	public void setText(String s) {
		if (mTextView != null) {
			mTextView.setText(s);
			mTextView.setTextColor(UNCHECKED_COLOR);
		}
	}

	public void setImage(int id, int width, int height) {
		if (mImageView != null) {
			mImageView.setImageResource(id);
			setImageSize(width, height);
		}
	}

	private void setImageSize(int w, int h) {
		if (mImageView != null) {
			ViewGroup.LayoutParams params = mImageView.getLayoutParams();

			params.width = dip2px(mContext, w);
			params.height = dip2px(mContext, h);
			mImageView.setLayoutParams(params);
		}
	}

	public void setChecked(int itemID) {
		if (mTextView != null) {
			mTextView.setTextColor(CHECKED_COLOR);
		}
		int checkDrawableId = -1;
		switch (itemID) {
			case Constant.BTN_FLAG_HOME:
				checkDrawableId = R.drawable.btn_home2;
				break;
			case Constant.BTN_FLAG_INFO:
				checkDrawableId = R.drawable.btn_info2;
				break;
			case Constant.BTN_FLAG_REPAIR:
				checkDrawableId = R.drawable.btn_logo;
				break;
			case Constant.BTN_FLAG_MARKET:
				checkDrawableId = R.drawable.btn_market2;
				break;
			case Constant.BTN_FLAG_FIND:
				checkDrawableId = R.drawable.btn_find2;
				break;
			default:
				break;
		}
		if (mImageView != null) {
			mImageView.setImageResource(checkDrawableId);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}
