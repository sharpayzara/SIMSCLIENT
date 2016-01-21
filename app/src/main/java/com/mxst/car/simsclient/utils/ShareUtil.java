package com.mxst.car.simsclient.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.mxst.car.simsclient.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 * author   Joy
 * Date:  2016/1/21.
 * version:  V1.0
 * Description:
 */
public class ShareUtil {
    private static Activity mCtx;
    public static PopupWindow popupWindow;
    public static String url, content, title;


    private static void initPopupWindow() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        View popuView = LayoutInflater.from(mCtx).inflate(R.layout.popwindow_share_address, null, false);
        popupWindow = new PopupWindow(popuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);


        popuView.findViewById(R.id.pop_cancel).setOnClickListener(myOnClickListener);
        popuView.findViewById(R.id.share_qq_tv).setOnClickListener(myOnClickListener);
        popuView.findViewById(R.id.share_circle_tv).setOnClickListener(myOnClickListener);
        popuView.findViewById(R.id.share_wechat_tv).setOnClickListener(myOnClickListener);

        popuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                return false;
            }
        });

    }



    private static UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.e("Joy", "分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Log.e("Joy", "分享失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Log.e("Joy", "分享取消");
        }
    };

    private static ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            new ShareAction(mCtx).setPlatform(share_media).setCallback(umShareListener)
                    .withText("多平台分享")
                    .share();
        }
    };

    public static void showPopupWindow(final Activity context, View v) {
        mCtx = context;
        if (popupWindow == null) {
            initPopupWindow();
        }
        if (!ShareUtil.popupWindow.isShowing()) {
            ShareUtil.popupWindow.showAtLocation(v, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        }
    }


    private static class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_cancel:
                    popupWindow.dismiss();
                    return;
                case R.id.share_qq_tv:
                    Toast.makeText(mCtx, content + "---" + url, Toast.LENGTH_SHORT).show();
                    new ShareAction(mCtx).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener)
                            .withText(content)
                            .withTitle(title)
                            .share();
                    return;
                case R.id.share_circle_tv:
                    new ShareAction(mCtx).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                            .withText(content)
                            .withTitle(title)
                            .share();
                    return;
                case R.id.share_wechat_tv:
                    new ShareAction(mCtx).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                            .withText(content)
                            .withTitle(title)
                            .share();
                    return;
            }
        }


    }
}

