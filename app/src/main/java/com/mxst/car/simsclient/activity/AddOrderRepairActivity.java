package com.mxst.car.simsclient.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.base.CommonHeadPanelFraActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddOrderRepairActivity extends CommonHeadPanelFraActivity implements View.OnClickListener {
    private ImageButton reset_btn, submit_btn;
    private Context mContext;
    private RadioButton by_radio;
    TextView pp_tv, md_tv, jg_tv, yydate_tv;
    EditText license_et, cx_et;
    private String brand, store, num, jgId, jgName;
    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date) {
            yydate_tv.setText(mFormatter.format(date));

        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_order_repair);
        super.onCreate(savedInstanceState);
        mContext = this;
        initUI();
    }

    private void initUI() {
        showBackBtn();
        setHeadTitle("新增预约");

        pp_tv = (TextView) findViewById(R.id.pp_tv);
        md_tv = (TextView) findViewById(R.id.md_tv);
        jg_tv = (TextView) findViewById(R.id.jg_tv);
        by_radio = (RadioButton) findViewById(R.id.by_radio);
        cx_et = (EditText) findViewById(R.id.cx_et);
        license_et = (EditText) findViewById(R.id.license_et);
        yydate_tv = (TextView) findViewById(R.id.yydate_tv);
        reset_btn = (ImageButton) findViewById(R.id.cancel_btn);
        submit_btn = (ImageButton) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);
        reset_btn.setOnClickListener(this);
        pp_tv.setOnClickListener(this);
        md_tv.setOnClickListener(this);
        jg_tv.setOnClickListener(this);
        yydate_tv.setOnClickListener(this);
        by_radio.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pp_tv:  //选择品牌
                startActivityForResult(new Intent(this, BrandChooseActivity.class), 1);
                break;
            case R.id.md_tv: //选择门店
                if (!TextUtils.isEmpty(brand)) {
                    Intent intent = new Intent(this, StoreChooseActivity.class);
                    intent.putExtra("brand", brand);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(this, "请先选择品牌", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.jg_tv: //选择技工
                if (!TextUtils.isEmpty(store)) {
                    Intent intent = new Intent(this, JGChooseActivity.class);
                    intent.putExtra("brand", brand);
                    intent.putExtra("store", store);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(this, "请先选择门店", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.yydate_tv: //预约时间
                new SlideDateTimePicker.Builder(this.getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        //.setMinDate(minDate)
                        //.setMaxDate(maxDate)
                        .setIs24HourTime(true)
                        //.setTheme(SlideDateTimePicker.HOLO_DARK)
                        //.setIndicatorColor(Color.parseColor("#990000"))
                        .build()
                        .show();
                break;
            case R.id.submit_btn: //提交
                submitBtn();
                break;
            case R.id.cancel_btn:
                cleanForm();
                break;
        }
    }

    private void cleanForm() {
        license_et.setText(null);
        cx_et.setText(null);
        yydate_tv.setText(null);
        pp_tv.setText(null);
        md_tv.setText(null);
        jg_tv.setText(null);
    }

    private void submitBtn() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("license", license_et.getText().toString());
        params.addQueryStringParameter("pp", pp_tv.getText().toString());
        params.addQueryStringParameter("cx", cx_et.getText().toString());
        if (by_radio.isChecked()) {
            params.addQueryStringParameter("wxlxName", "保养");
        } else {
            params.addQueryStringParameter("wxlxName", "一般维修");
        }

        params.addQueryStringParameter("fixno", num);
        params.addQueryStringParameter("fixName", md_tv.getText().toString());
        params.addQueryStringParameter("human", jgId);
        params.addQueryStringParameter("humanname", jg_tv.getText().toString());
        params.addQueryStringParameter("yydate", yydate_tv.getText().toString());
        new BaseTask<JsonResult<String>, String>(mContext, R.string.upload_notice) {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<String>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    setResult(Constant.REQUESTCODE.ORDEREPIREBACK);
                    finish();
                } else {
                    CommonUtil.showToastToShort(mContext, result.getMsg());
                }
            }
        }.requestByPost(Constant.URL.SAVEYY, params);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Constant.REQUESTCODE.CHOOSEBRAND:
                brand = data.getStringExtra("brand");
                pp_tv.setText(brand);
                if (!md_tv.getText().toString().isEmpty()) {
                    md_tv.setText(null);
                }
                if (!jg_tv.getText().toString().isEmpty()) {
                    jg_tv.setText(null);
                }
                break;
            case Constant.REQUESTCODE.CHOOSESTORE:
                store = data.getStringExtra("store");
                num = data.getStringExtra("num");
                md_tv.setText(store);
                if (!jg_tv.getText().toString().isEmpty()) {
                    jg_tv.setText(null);
                }

                break;
            case Constant.REQUESTCODE.CHOOSEJG:
                jgId = data.getStringExtra("jgNum");
                jgName = data.getStringExtra("jgName");
                jg_tv.setText(jgName);
                break;
        }
    }
}