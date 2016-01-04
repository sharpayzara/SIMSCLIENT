package com.mxst.car.simsclient.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

public class BottomControlPanel extends RelativeLayout implements View.OnClickListener{
	//private Context mContext;
	private ImageText mHomedBtn = null;
	private ImageText mInfoBtn = null;
	private ImageText mFindBtn = null;
	private ImageText mMarketBtn = null;
	private ImageView mLogoBtn= null;
	private int DEFALUT_BACKGROUND_COLOR = Color.rgb(243, 243, 243);
	private BottomPanelCallback mBottomCallback = null;
	private List<View> viewList = new ArrayList<View>();

	public interface BottomPanelCallback{
		public void onBottomPanelClick(int itemId);
	}

	public BottomControlPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mHomedBtn = (ImageText)findViewById(R.id.btn_home);
		mInfoBtn = (ImageText)findViewById(R.id.btn_info);
		mLogoBtn = (ImageView)findViewById(R.id.btn_logo);
		mFindBtn = (ImageText)findViewById(R.id.btn_find);
		mMarketBtn = (ImageText)findViewById(R.id.btn_market);
		//setBackgroundColor(DEFALUT_BACKGROUND_COLOR);
		viewList.add(mHomedBtn);
		viewList.add(mInfoBtn);
		viewList.add(mLogoBtn);
		viewList.add(mFindBtn);
		viewList.add(mMarketBtn);
	}
	public void initBottomPanel(){
		if(mHomedBtn != null){
			mHomedBtn.setImage(R.drawable.btn_home);
			mHomedBtn.setText("首页");
		}
		if(mInfoBtn != null){
			mInfoBtn.setImage(R.drawable.btn_info);
			mInfoBtn.setText("资讯");
		}

		if(mLogoBtn != null){
			//mLogoBtn.setImage(R.drawable.btn_logo,30,30);//,45,45
		}
		if(mFindBtn != null){
			mFindBtn.setImage(R.drawable.btn_find);
			mFindBtn.setText("找车");
		}
		if(mMarketBtn != null){
			mMarketBtn.setImage(R.drawable.btn_market);
			mMarketBtn.setText("积分商城");
		}
		setBtnListener();
	}

	private void setBtnListener(){
		int num = this.getChildCount();
		for(int i = 0; i < num; i++){
			View v = getChildAt(i);
			if(v != null){
				v.setOnClickListener(this);
			}
		}
	}

	public void setBottomCallback(BottomPanelCallback bottomCallback){
		mBottomCallback = bottomCallback;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		initBottomPanel();
		int index = -1;
		switch(v.getId()){
			case R.id.btn_home:
				index = Constant.BTN_FLAG_HOME;
				mHomedBtn.setChecked(Constant.BTN_FLAG_HOME);
				break;
			case R.id.btn_info:
				index = Constant.BTN_FLAG_INFO;
				mInfoBtn.setChecked(Constant.BTN_FLAG_INFO);
				break;
			case R.id.btn_logo:
				index = Constant.BTN_FLAG_REPAIR;
				//mLogoBtn.setChecked(Constant.BTN_FLAG_REPAIR);
				break;
			case R.id.btn_find:
				index = Constant.BTN_FLAG_FIND;
				mFindBtn.setChecked(Constant.BTN_FLAG_FIND);
				break;
			case R.id.btn_market:
				index = Constant.BTN_FLAG_MARKET;
				mMarketBtn.setChecked(Constant.BTN_FLAG_MARKET);
				break;
			default:break;
		}
		if(mBottomCallback != null){
			mBottomCallback.onBottomPanelClick(index);
		}
	}

	public void defaultBtnChecked(){
		if(mHomedBtn != null){
			mHomedBtn.setChecked(Constant.BTN_FLAG_HOME);
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		layoutItems(left, top, right, bottom);
	}
	/**最左边和最右边的view由母布局的padding进行控制位置。这里需对第2、3个view的位置重新设置
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	private void layoutItems(int left, int top, int right, int bottom){
		int n = getChildCount();
		int widthPx = SizeUtils.getSysWidthPx(this.getContext());
		int eachWidth = widthPx / n;
		for (int i = 0; i < n; i++) {
			LayoutParams tempParams = (LayoutParams) viewList.get(i)
					.getLayoutParams();
			tempParams.width = eachWidth;
			viewList.get(i).setLayoutParams(tempParams);
		}
	}
}
