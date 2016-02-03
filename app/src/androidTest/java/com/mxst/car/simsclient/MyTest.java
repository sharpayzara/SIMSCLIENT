package com.mxst.car.simsclient;

import android.test.InstrumentationTestCase;

import com.mxst.car.simsclient.utils.CommonUtil;

/**
 * author   Jhong
 * Date:  2016/2/3.
 * version:  V1.0
 * Description:
 */
public class MyTest extends InstrumentationTestCase {

    public void  testUnit(){
        CommonUtil.switchRatingValue(4);
        CommonUtil.switchRatingValue(4.1f);
        CommonUtil.switchRatingValue(4.5f);
        CommonUtil.switchRatingValue(4.6f);
    }
}
