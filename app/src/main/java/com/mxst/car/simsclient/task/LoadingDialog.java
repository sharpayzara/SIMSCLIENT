package com.mxst.car.simsclient.task;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxst.car.simsclient.R;

public class LoadingDialog extends Dialog {
    private ImageView  mLoadingImageView;
    private TextView loadTextView;
    private Animation mLoadingAnimation;
    private String loadText;
    public LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener, String loadText) {
        super(context, cancelable, cancelListener);
        this.loadText = loadText;
    }
 
    public LoadingDialog(Context context, int theme, String loadText) {
        super(context, theme);
        this.loadText = loadText;
    }
 
    public LoadingDialog(Context context, String loadText) {
        super(context);
        this.loadText = loadText;
    }
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading_dialog);
        mLoadingImageView=  (ImageView) findViewById(R.id.loadingImageView);
        loadTextView = (TextView) findViewById(R.id.loadText);
        if(TextUtils.isEmpty(loadText)){
            loadTextView.setVisibility(View.GONE);
        }else{
            loadTextView.setText(loadText);
        }
    }
     
    @Override
    public void show() {
        super.show();
        mLoadingAnimation=AnimationUtils.loadAnimation(getContext(), R.anim.loading_dialog_progressbar);
        mLoadingImageView.startAnimation(mLoadingAnimation);
    }
    @Override
    public void dismiss() {
        super.dismiss();
        mLoadingAnimation.cancel();
    }
 
}