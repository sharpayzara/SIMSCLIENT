package com.mxst.car.simsclient.utils;

import android.content.Context;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.service.PreferenceService;

import java.util.Date;

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
    public static Long getCurrentDate(){
        return new Date().getTime();
    }
    public static boolean judgeTokenValid(Context context){
        PreferenceService ps = new PreferenceService(context);
        Long intervalTime = CommonUtil.getCurrentDate() - ps.getLoginDate();
        if(intervalTime < 86400000){
            return true;
        }
       /* if(intervalTime < 5000){
            return true;
        }*/
        return false;
    }

}
