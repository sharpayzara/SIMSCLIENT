package com.mxst.car.simsclient.utils;

import android.content.Context;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;

public class CommonUtil {
    private static BitmapUtils utils;
    public static void showToastToShort(Context mContext,String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
    public static BitmapUtils getBitMapUtils(Context mContext){
        if(utils == null){
            utils = new BitmapUtils(mContext.getApplicationContext());
            utils.configDefaultLoadFailedImage(R.drawable.plugin_img);
        }
        return utils;
    }
    public static void setRatingBar(Context mContext,long value){

    }
}
