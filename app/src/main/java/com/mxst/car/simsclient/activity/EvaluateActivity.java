package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.EvaluateList;
import com.mxst.car.simsclient.layout.RoundImageView;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;

import java.io.Serializable;
import java.util.List;

public class EvaluateActivity extends CommonHeadPanelActivity implements View.OnClickListener{
    Context mContext;
    ImageButton submit_btn;
    EditText evaluate_et;
    RatingBar ratingBar;
    List<EvaluateList.Evaluate> list;
    EvaluateList.Evaluate entity;
    TextView name,phone,pp,cx,loginNo,noteDate,license,wxlx,gls;
    RoundImageView headImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_evaluate);
        super.onCreate(savedInstanceState);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(4);
        mContext = this;
        initUI();
        initData();
    }

    private void initData() {
        list =  (List<EvaluateList.Evaluate>) getIntent().getSerializableExtra("list");
        entity = list.get(0);
        name.setText(entity.getArtisan().getName());
        phone.setText(entity.getArtisan().getPhone());
        pp.setText(entity.getPp());
        cx.setText(entity.getCx());
        loginNo.setText(entity.getLoginNo());
        noteDate.setText(entity.getNoteDate());
        license.setText(entity.getLicense());
        wxlx.setText(entity.getWxlx());
        gls.setText(entity.getGls());
        CommonUtil.getBitMapUtils(this).display(headImg,entity.getArtisan().getHeadPortrait());
    }

    private void initUI() {
        setHeadTitle("技工评价");
        setCanBack(false);
        evaluate_et = (EditText) findViewById(R.id.evaluate_et);
        evaluate_et.clearFocus();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        submit_btn = (ImageButton) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);
        headImg = (RoundImageView) findViewById(R.id.headImg);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        pp = (TextView) findViewById(R.id.pp);
        cx = (TextView) findViewById(R.id.cx);
        loginNo = (TextView) findViewById(R.id.loginNo);
        noteDate = (TextView) findViewById(R.id.noteDate);
        license = (TextView) findViewById(R.id.license);
        wxlx = (TextView) findViewById(R.id.wxlx);
        gls = (TextView) findViewById(R.id.gls);
    }



    @Override
    public void onClick(View v) {
        if(v == submit_btn){
            if(evaluate_et.getText().toString().length() > 125){
                CommonUtil.showToastToShort(this,"评论不得超过125个字");
                return ;
            }else if(evaluate_et.getText().toString().length() == 0){
                CommonUtil.showToastToShort(this,"评论不能为空");
                return ;
            }
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("loginNo",list.get(0).getLoginNo());
            params.addQueryStringParameter("jigongId",list.get(0).getArtisan().getId());
            params.addQueryStringParameter("content",evaluate_et.getText().toString());
            params.addQueryStringParameter("star",ratingBar.getRating() + "");
            new BaseTask<JsonResult<String>,String>(mContext,R.string.upload_notice){

                @Override
                public TypeToken setTypeToken() {
                    return new TypeToken<String>(){};
                }

                @Override
                public void onSuccess() {
                    if(result.isSuccess()){
                        list.remove(0);
                        if(list.size() > 0){
                            Intent intent = new Intent(mContext, EvaluateActivity.class);
                            intent.putExtra("list", (Serializable) list);
                            startActivity(intent);
                        }
                        finish();
                    }else{
                        CommonUtil.showToastToShort(mContext,result.getMsg());
                    }
                }
            }.requestByPost(Constant.URL.SAVEEVALUATE,params);
        }
    }
    @Override
    public void onBackPressed() {

    }
}
