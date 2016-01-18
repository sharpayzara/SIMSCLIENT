package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.InsureItemDetail;
import com.mxst.car.simsclient.utils.Constant;

import java.util.List;

public class InsureItemDetailActivity extends CommonHeadPanelActivity{
    Context mContext;
    TextView pp,brandNo,sxbDate,license,name,jghj,qd;
    LayoutInflater inflater;
    LinearLayout hd_llt,xm_llt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_insure__item_detail);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        initData();
    }

    private void initUI() {
        setHeadTitle("保险查询");
        showBackBtn();
        hd_llt = (LinearLayout) findViewById(R.id.hd_llt);
        xm_llt = (LinearLayout) findViewById(R.id.xm_llt);
        pp = (TextView) findViewById(R.id.pp);
        brandNo = (TextView) findViewById(R.id.brandNo);
        sxbDate = (TextView) findViewById(R.id.sxbDate);
        license = (TextView) findViewById(R.id.license);
        name = (TextView) findViewById(R.id.name);
        jghj = (TextView) findViewById(R.id.jghj);
        qd = (TextView) findViewById(R.id.qd);
    }
    private void initData() {
        inflater = LayoutInflater.from(mContext);
        pp.setText(getIntent().getStringExtra("pp"));
        brandNo.setText(getIntent().getStringExtra("brandNo"));
        sxbDate.setText(getIntent().getStringExtra("sxbDate"));
        license.setText(getIntent().getStringExtra("license"));
        name.setText(getIntent().getStringExtra("name"));
        loadData();
    }

    private void loadData() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("noteNo",getIntent().getStringExtra("noteNo"));
        new BaseTask<JsonResult<InsureItemDetail>,String>(mContext,R.string.download_notice){
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<InsureItemDetail>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    qd.setText(result.getRecord().getQd());
                    jghj.setText(result.getRecord().getJghj());
                    List<String> hdList = result.getRecord().getHdList();
                    for(String str : hdList){
                        View view =  inflater.inflate(R.layout.item_insure_hd,null);
                        ((TextView)view.findViewById(R.id.hd_tv)).setText(str);
                        hd_llt.addView(view);
                    }
                    List<InsureItemDetail.XmListEntity> xmList = result.getRecord().getXmList();
                    for(InsureItemDetail.XmListEntity entity : xmList){
                        View view =  inflater.inflate(R.layout.item_insure_xm,null);
                        ((TextView)view.findViewById(R.id.val_tv)).setText(entity.getVal());
                        ((TextView)view.findViewById(R.id.xmm_tv)).setText(entity.getXmm());
                        xm_llt.addView(view);
                    }
                    List<String> xm2List = result.getRecord().getXm2List();
                    for(String str : xm2List){
                        View view =  inflater.inflate(R.layout.item_insure_xm2,null);
                        ((TextView)view.findViewById(R.id.xm_tv)).setText(str);
                        xm_llt.addView(view);
                    }
                }
            }
        }.requestByPost(Constant.URL.INSUREITEMDETAIL,params);
    }
}
