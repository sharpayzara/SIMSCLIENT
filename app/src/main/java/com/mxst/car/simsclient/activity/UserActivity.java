package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelActivity;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;

public class UserActivity extends CommonHeadPanelActivity implements View.OnClickListener {
    Context mContext;
    TextView nickName, phone;
    LinearLayout headImg_layout, collect_lly, setup_lly, score_llt, account_llt;
    BitmapUtils bitmapUtils;
    ImageView headimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
        initData();
    }

    private void initData() {

    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("个人中心");
        setZX();
        headImg_layout = (LinearLayout) findViewById(R.id.headImg_layout);
        headImg_layout.setOnClickListener(this);
        collect_lly = (LinearLayout) findViewById(R.id.collect_lly);
        collect_lly.setOnClickListener(this);
        setup_lly = (LinearLayout) findViewById(R.id.setup_lly);
        setup_lly.setOnClickListener(this);
        score_llt = (LinearLayout) findViewById(R.id.score_llt);
        score_llt.setOnClickListener(this);
        account_llt = (LinearLayout) findViewById(R.id.account_llt);
        account_llt.setOnClickListener(this);
        nickName = (TextView) findViewById(R.id.nickName);
        phone = (TextView) findViewById(R.id.phone);
        nickName.setText(getIntent().getStringExtra("nickName"));
        phone.setText(getIntent().getStringExtra("phone"));
        headimg = (ImageView) findViewById(R.id.headImg);
        bitmapUtils = new BitmapUtils(this);
        bitmapUtils.display(headimg, getIntent().getStringExtra("img"));
    }


    @Override
    public void onClick(View v) {
        if (v == headImg_layout || v == account_llt) {
            Intent intent = new Intent(mContext, UserInfoActivity.class);
            startActivityForResult(intent, 1);
        } else if (v == collect_lly) {
            Intent intent = new Intent(mContext, CollectActivity.class);
            intent.putExtra("img", getIntent().getStringExtra("img"));
            intent.putExtra("nickName", getIntent().getStringExtra("nickName"));
            intent.putExtra("phone", getIntent().getStringExtra("phone"));
            mContext.startActivity(intent);
        } else if (v == setup_lly) {
            Intent intent = new Intent(mContext, UserSetUpActivity.class);
            mContext.startActivity(intent);
        } else if (v == score_llt) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.REQUESTCODE.NICKNAME) {
            CommonUtil.getBitMapUtils(this).display(headimg, data.getStringExtra("img"));
            nickName.setText(data.getStringExtra("name"));
        }
    }
}
