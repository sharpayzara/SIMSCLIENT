package com.mxst.car.simsclient.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class SizeUtils {
	/**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */ 
    public static int dip2px(Context context, float dpValue) { 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int) (dpValue * scale + 0.5f); 
    }  
    
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    /**
     * 获取屏幕的宽度
     * @param context
     * @return
     */
    public static int getSysWidthPx(Context context){
    	 DisplayMetrics dm = context.getResources().getDisplayMetrics();
         return dm.widthPixels;
    }
    
    /**
     * 获取屏幕的高度
     * @param context
     * @return
     */
    public static int getSysHeightPx(Context context){
    	DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;     
   }
}
