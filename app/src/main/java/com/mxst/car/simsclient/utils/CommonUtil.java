package com.mxst.car.simsclient.utils;

import android.content.Context;
import android.widget.Toast;

public class CommonUtil {

    public static void showToastToShort(Context mContext,String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
