package com.mxst.car.simsclient.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickindexBar extends View {

	private Paint paint;

	// 单元格的高度
	private float cellHeigth;
	private float cellwidth;

	private static String[] strs = new String[] { "★","A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	public QuickindexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public QuickindexBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public QuickindexBar(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int j = 0; j < strs.length; j++) {

			// 获取到X轴的坐标

			int x = (int) (cellwidth / 2 - paint.measureText(strs[j]) / 2);

			// 获取到文字的高度
			Rect bounds = new Rect();
			paint.getTextBounds(strs[j], 0, strs[j].length(), bounds);
			float textHeigth = bounds.height();

			// 获取到Y轴的坐标

			int y = (int) (cellHeigth / 2 + textHeigth / 2 + j * cellHeigth);

			paint.setColor(touchIndex == j ? Color.BLACK : Color.RED);
			
			canvas.drawText(strs[j], x, y, paint);
		}
		super.onDraw(canvas);

	}

	/* 在这个方法中测量控件的宽高 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// 得到每个单元格的高度
		cellHeigth = getHeight() / strs.length;
		//得到单元格的宽度
		cellwidth = getMeasuredWidth();
	}

	private int touchIndex = -1;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (MotionEventCompat.getActionMasked(event)) {

		case MotionEvent.ACTION_UP:
			touchIndex = -1;
			break;

		default:
			// 手指触摸的高度
			float f = event.getY();
			int index = (int) (f / cellHeigth);
			if (index >= 0 && index < strs.length) {
				if (touchIndex != index) {
					System.out.println(strs[index]);
					listener.onBack(strs[index]);
					touchIndex = index;
				}
			}
			break;
		}
		// 手指触摸的高度
		invalidate();
		return true;
	}

	/**
	 * 滑动的回调接口
	 *
	 * @author Administrator
	 *
	 */
	public interface OnSlideTouchListener {
		void onBack(String str);
	}

	public void setOnSlideTouchListener(OnSlideTouchListener listener) {
		this.listener = listener;
	}

	private OnSlideTouchListener listener;
}
