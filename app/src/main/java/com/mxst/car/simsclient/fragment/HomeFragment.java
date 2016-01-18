package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.MainActivity;
import com.mxst.car.simsclient.activity.RecommendActivity;
import com.mxst.car.simsclient.activity.UserActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.HomeInfoEntity;
import com.mxst.car.simsclient.layout.ClearEditText;
import com.mxst.car.simsclient.service.PreferenceService;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.CryptTool;

import org.json.JSONObject;

import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener {
    RelativeLayout login_layout, user_layout;
    LinearLayout user_btn, cx_more_lly, zx_more_lly, market_more_lly, recommend_lin;
    ClearEditText user_et, pwd_et;
    LayoutInflater inflater;
    Context mContext;
    TextView zx_title_1, zx_title_2, zx_content_1, zx_content_2;
    ImageView rmcx_iv_1, rmcx_iv_2, rmcx_iv_3, rmcx_iv_4, rmcx_iv_5, zx_iv_1, zx_iv_2, headImg;
    BitmapUtils utils;
    Button login_btn;
    TextView phone, jifen, continueQd, nickName, cjNum, recNum, qdFlg;
    MainActivity mainActivity;
    PreferenceService ps;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        this.inflater = inflater;
        ps = new PreferenceService(mContext);
        initUI();
        initData();
        return root;
    }

    private void initUI() {
        login_layout = (RelativeLayout) root.findViewById(R.id.login_layout);
        user_layout = (RelativeLayout) root.findViewById(R.id.user_layout);
        rmcx_iv_1 = (ImageView) root.findViewById(R.id.rmcx_iv_1);
        rmcx_iv_2 = (ImageView) root.findViewById(R.id.rmcx_iv_2);
        rmcx_iv_3 = (ImageView) root.findViewById(R.id.rmcx_iv_3);
        rmcx_iv_4 = (ImageView) root.findViewById(R.id.rmcx_iv_4);
        rmcx_iv_5 = (ImageView) root.findViewById(R.id.rmcx_iv_5);
        zx_iv_1 = (ImageView) root.findViewById(R.id.zx_iv_1);
        zx_iv_1.setDrawingCacheEnabled(true);
        zx_iv_2 = (ImageView) root.findViewById(R.id.zx_iv_2);
        zx_iv_2.setDrawingCacheEnabled(true);
        zx_title_1 = (TextView) root.findViewById(R.id.zx_title_1);
        zx_title_2 = (TextView) root.findViewById(R.id.zx_title_2);
        zx_content_1 = (TextView) root.findViewById(R.id.zx_content_1);
        zx_content_2 = (TextView) root.findViewById(R.id.zx_content_2);
        user_et = (ClearEditText) root.findViewById(R.id.user_et);
        pwd_et = (ClearEditText) root.findViewById(R.id.pwd_et);
        user_btn = (LinearLayout) root.findViewById(R.id.user_btn);
        pwd_et.setText(ps.getPassword());
        user_et.setText(ps.getUserName());
        ps.savePassword(pwd_et.getText().toString());
        login_btn = (Button) root.findViewById(R.id.login_btn);

        headImg = (ImageView) root.findViewById(R.id.headImg);
        phone = (TextView) root.findViewById(R.id.phone);
        jifen = (TextView) root.findViewById(R.id.jifen);
        continueQd = (TextView) root.findViewById(R.id.continueQd);
        nickName = (TextView) root.findViewById(R.id.nickName);
        cjNum = (TextView) root.findViewById(R.id.cjNum);
        recNum = (TextView) root.findViewById(R.id.recNum);
        qdFlg = (TextView) root.findViewById(R.id.qdFlg);
        recommend_lin = (LinearLayout) root.findViewById(R.id.recommend_lin);
        cx_more_lly = (LinearLayout) root.findViewById(R.id.cx_more_lly);
        zx_more_lly = (LinearLayout) root.findViewById(R.id.zx_more_lly);
        market_more_lly = (LinearLayout) root.findViewById(R.id.market_more_lly);
        cx_more_lly.setOnClickListener(this);
        zx_more_lly.setOnClickListener(this);
        market_more_lly.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        user_btn.setOnClickListener(this);
        qdFlg.setOnClickListener(this);
        recommend_lin.setOnClickListener(this);
        zx_iv_1.setOnClickListener(this);
        zx_iv_2.setOnClickListener(this);
        judgeIsLogin();
    }

    private void judgeIsLogin() {
        if(!CommonUtil.judgeTokenValid(mContext)){
            login_layout.setVisibility(View.VISIBLE);
            user_layout.setVisibility(View.GONE);
        }else if(CommonUtil.judgeTokenValid(mContext) && TextUtils.isEmpty(Constant.AUTHENTICATION_TOKEN)){
            login_layout.setVisibility(View.VISIBLE);
            user_layout.setVisibility(View.GONE);
            doLogin();
        }else{
            login_layout.setVisibility(View.GONE);
            user_layout.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        utils = new BitmapUtils(mContext);
        utils.configDefaultLoadFailedImage(R.drawable.plugin_img);
        loadData();
    }


    private void loadData() {
        new BaseTask<JsonResult<HomeInfoEntity>, String>(mContext) {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<HomeInfoEntity>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    if (result.getRecord().getHotBrand().size() >= 5) {
                        utils.display(rmcx_iv_1, result.getRecord().getHotBrand().get(0).getImg());
                        utils.display(rmcx_iv_2, result.getRecord().getHotBrand().get(1).getImg());
                        utils.display(rmcx_iv_3, result.getRecord().getHotBrand().get(2).getImg());
                        utils.display(rmcx_iv_4, result.getRecord().getHotBrand().get(3).getImg());
                        utils.display(rmcx_iv_5, result.getRecord().getHotBrand().get(4).getImg());

                    }
                    if (result.getRecord().getZxs().size() >= 2) {
                        utils.display(zx_iv_1, result.getRecord().getZxs().get(0).getImg());
                        utils.display(zx_iv_2, result.getRecord().getZxs().get(1).getImg());
                        zx_title_1.setText(result.getRecord().getZxs().get(0).getTitle());
                        zx_title_2.setText(result.getRecord().getZxs().get(1).getTitle());
                        zx_content_1.setText(result.getRecord().getZxs().get(0).getSubtitle());
                        zx_content_2.setText(result.getRecord().getZxs().get(1).getSubtitle());
                    }
                    if (result.getRecord().getVipInfo() != null) {
                        phone.setText(result.getRecord().getVipInfo().getPhone());
                        jifen.setText(result.getRecord().getVipInfo().getJifen());
                        continueQd.setText(result.getRecord().getVipInfo().getContinueQd());
                        nickName.setText(result.getRecord().getVipInfo().getNickName());
                        utils.display(headImg, result.getRecord().getVipInfo().getHeadImg());
                    }
                    cjNum.setText(result.getRecord().getCjNum() + "");
                    recNum.setText(result.getRecord().getRecNum() + "");
                    if (result.getRecord().getQdFlg() == 1) {
                        qdFlg.setText("已签到");
                    }
                }
            }
        }.requestByPost(Constant.URL.INDEXINFO, new RequestParams());
    }

    @Override
    public void onClick(View v) {
        if (v == login_btn) {
            doLogin();
        } else if (user_btn == v) {
            Intent intent = new Intent(mContext, UserActivity.class);
            mContext.startActivity(intent);
        } else if (v == qdFlg) {
            if (qdFlg.getText().toString().equals("本日签到")) {
                new BaseTask<JsonResult<String>, String>(mContext) {

                    @Override
                    public TypeToken setTypeToken() {
                        return new TypeToken<String>() {
                        };
                    }

                    @Override
                    public void onSuccess() {
                        if (result.isSuccess()) {
                            qdFlg.setText("已签到");
                        }
                    }
                }.requestByPost(Constant.URL.QIANDAO, new RequestParams());
            } else {
                CommonUtil.showToastToShort(mContext, "今日已签到");
            }
        } else if (cx_more_lly == v) {

        } else if (zx_more_lly == v) {
            mainActivity.setDynamicFragment(Constant.FRAGMENT_FLAG_INFO);
        }/* else if (zx_iv_1 == v) {
            Intent intent = new Intent(mContext, ViewImageActivity.class);
            ArrayList<Bitmap> tempList = new ArrayList();
            tempList.add(zx_iv_1.getDrawingCache());
            intent.putParcelableArrayListExtra("imgList", tempList);
            mContext.startActivity(intent);
        } else if (zx_iv_2 == v) {
            Intent intent = new Intent(mContext, ViewImageActivity.class);
            ArrayList<Bitmap> tempList = new ArrayList();
            tempList.add(zx_iv_1.getDrawingCache());
            intent.putParcelableArrayListExtra("imgList", tempList);
            mContext.startActivity(intent);
        } */else if (recommend_lin == v) {
            Intent intent = new Intent(mContext, RecommendActivity.class);
            mContext.startActivity(intent);
        }
    }

    public void doLogin(){
        if (!TextUtils.isEmpty(user_et.getText().toString())
                && !TextUtils.isEmpty(pwd_et.getText().toString())) {
            RequestParams params = new RequestParams();
            try {
                params.addQueryStringParameter("phone", CryptTool.md5Digest(user_et.getText().toString()));
                params.addQueryStringParameter("password", CryptTool.md5Digest(pwd_et.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            new BaseTask<JsonResult<JSONObject>, String>(mContext, R.string.login_notice) {

                @Override
                public TypeToken setTypeToken() {
                    return new TypeToken<JSONObject>() {
                    };
                }

                @Override
                public void onSuccess() {
                    if (result.isSuccess()) {
                        Constant.AUTHENTICATION_TOKEN = result.getRecord().optString("authenticationToken");
                        ps.saveLoginDate(new Date().getTime());
                        ps.saveUserName(user_et.getText().toString());
                        ps.savePassword(pwd_et.getText().toString());
                        CommonUtil.showToastToShort(mContext, "登录成功");
                        Log.e("Joy", Constant.AUTHENTICATION_TOKEN);
                        login_layout.setVisibility(View.GONE);
                        user_layout.setVisibility(View.VISIBLE);
                        loadData();
                    } else {
                        CommonUtil.showToastToShort(mContext, result.getMsg());
                    }
                }
            }.requestByPost(Constant.URL.LOGIN, params);
        } else {
            CommonUtil.showToastToShort(mContext, "用户名或密码不能为空！");
        }
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
