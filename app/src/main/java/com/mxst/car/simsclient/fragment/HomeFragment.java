package com.mxst.car.simsclient.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.BrandFindActivity;
import com.mxst.car.simsclient.activity.MainActivity;
import com.mxst.car.simsclient.activity.NewsInfoActivity;
import com.mxst.car.simsclient.activity.RecommendActivity;
import com.mxst.car.simsclient.activity.RecommendKFActivity;
import com.mxst.car.simsclient.activity.TradeListActivity;
import com.mxst.car.simsclient.activity.UserActivity;
import com.mxst.car.simsclient.activity.UserScoreActivity;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.HomeInfoEntity;
import com.mxst.car.simsclient.layout.ClearEditText;
import com.mxst.car.simsclient.service.PreferenceService;
import com.mxst.car.simsclient.utils.CommonUtil;
import com.mxst.car.simsclient.utils.Constant;
import com.mxst.car.simsclient.utils.CryptTool;
import com.mxst.car.simsclient.utils.SizeUtils;
import com.mxst.car.simsclient.utils.TimeCountUtil;

import org.json.JSONObject;

import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener{
    RelativeLayout login_layout, user_layout;
    LinearLayout user_btn, cx_more_lly, zx_more_lly, market_more_lly, recommend_lin, jifenllt, car_group_llt, zx1_llt, zx2_llt, trade_llt;
    ClearEditText user_et;
    EditText pwd_et;
    LayoutInflater inflater;
    Context mContext;
    TextView zx_title_1, zx_title_2, zx_content_1, zx_content_2, tjkf;
    ImageView zx_iv_1, zx_iv_2, headImg, diamond1, diamond2, diamond3;
    BitmapUtils utils;
    Button login_btn, obtain_password;
    TextView phone, jifen, continueQd, nickName, cjNum, recNum, qdFlg;
    MainActivity mainActivity;
    PreferenceService ps;
    private View root;
    private String zx1Id, zx2Id;
    CountDownTimer timeCountUtil;
    private String img, content1, content2, title1, title2;


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

    public void onResume() {
        super.onResume();
        loadData();
    }

    private void initUI() {
        jifenllt = (LinearLayout) root.findViewById(R.id.jifenllt);
        login_layout = (RelativeLayout) root.findViewById(R.id.login_layout);
        user_layout = (RelativeLayout) root.findViewById(R.id.user_layout);
        zx_iv_1 = (ImageView) root.findViewById(R.id.zx_iv_1);
        zx_iv_1.setDrawingCacheEnabled(true);
        zx_iv_2 = (ImageView) root.findViewById(R.id.zx_iv_2);
        zx_iv_2.setDrawingCacheEnabled(true);
        zx_title_1 = (TextView) root.findViewById(R.id.zx_title_1);
        zx_title_2 = (TextView) root.findViewById(R.id.zx_title_2);
        zx_content_1 = (TextView) root.findViewById(R.id.zx_content_1);
        zx_content_2 = (TextView) root.findViewById(R.id.zx_content_2);
        user_et = (ClearEditText) root.findViewById(R.id.user_et);
        pwd_et = (EditText) root.findViewById(R.id.pwd_et);
        obtain_password = (Button) root.findViewById(R.id.obtain_password);
        user_btn = (LinearLayout) root.findViewById(R.id.user_btn);
        pwd_et.setText(ps.getPassword());
        user_et.setText(ps.getUserName());
        ps.savePassword(pwd_et.getText().toString());
        login_btn = (Button) root.findViewById(R.id.login_btn);
        car_group_llt = (LinearLayout) root.findViewById(R.id.car_group_llt);
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
        zx1_llt = (LinearLayout) root.findViewById(R.id.zx1_llt);
        zx2_llt = (LinearLayout) root.findViewById(R.id.zx2_llt);
        diamond1 = (ImageView) root.findViewById(R.id.diamond1);
        diamond2 = (ImageView) root.findViewById(R.id.diamond2);
        diamond3 = (ImageView) root.findViewById(R.id.diamond3);
        trade_llt = (LinearLayout) root.findViewById(R.id.trade_llt);
        tjkf = (TextView) root.findViewById(R.id.tjkf);
        trade_llt.setOnClickListener(this);
        tjkf.setOnClickListener(this);
        cx_more_lly.setOnClickListener(this);
        zx_more_lly.setOnClickListener(this);
        market_more_lly.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        user_btn.setOnClickListener(this);
        qdFlg.setOnClickListener(this);
        jifenllt.setOnClickListener(this);
        recommend_lin.setOnClickListener(this);
        zx_iv_1.setOnClickListener(this);
        zx_iv_2.setOnClickListener(this);
        zx2_llt.setOnClickListener(this);
        zx1_llt.setOnClickListener(this);
        obtain_password.setOnClickListener(this);
        judgeIsLogin();
    }

    private void judgeIsLogin() {
        if (!CommonUtil.judgeTokenValid(mContext)) {
            login_layout.setVisibility(View.VISIBLE);
            user_layout.setVisibility(View.GONE);
        } else if (CommonUtil.judgeTokenValid(mContext) && TextUtils.isEmpty(Constant.AUTHENTICATION_TOKEN)) {
            login_layout.setVisibility(View.VISIBLE);
            user_layout.setVisibility(View.GONE);
            doLogin();
        } else {
            login_layout.setVisibility(View.GONE);
            user_layout.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        utils = new BitmapUtils(mContext);
        utils.configDefaultLoadFailedImage(R.drawable.plugin_img);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            loadData();
        }
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
                      /*  utils.display(rmcx_iv_1, result.getRecord().getHotBrand().get(0).getImg());
                        utils.display(rmcx_iv_2, result.getRecord().getHotBrand().get(1).getImg());
                        utils.display(rmcx_iv_3, result.getRecord().getHotBrand().get(2).getImg());
                        utils.display(rmcx_iv_4, result.getRecord().getHotBrand().get(3).getImg());
                        utils.display(rmcx_iv_5, result.getRecord().getHotBrand().get(4).getImg());
*/
                        LayoutInflater inflater = LayoutInflater.from(mContext);
                        car_group_llt.removeAllViews();
                        for (final HomeInfoEntity.HotBrandEntity entity : result.getRecord().getHotBrand()) {
                            View view = inflater.inflate(R.layout.item_car_view, null);
                            utils.display(view.findViewById(R.id.rmcx_iv), entity.getImg());
                            // car_group_llt.addView(view);
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), BrandFindActivity.class);
                                    intent.putExtra("brand", entity.getBrand());
                                    startActivity(intent);
                                }
                            });
                            car_group_llt.addView(view, new ViewGroup.LayoutParams(SizeUtils.dip2px(mContext, 60), ViewGroup.LayoutParams.MATCH_PARENT));
                        }

                    }
                    if (result.getRecord().getZxs().size() >= 2) {
                        utils.display(zx_iv_1, result.getRecord().getZxs().get(0).getImg());
                        utils.display(zx_iv_2, result.getRecord().getZxs().get(1).getImg());
                        zx_title_1.setText(result.getRecord().getZxs().get(0).getTitle());
                        title1 = result.getRecord().getZxs().get(0).getTitle();
                        title2 = result.getRecord().getZxs().get(1).getTitle();
                        zx_title_2.setText(result.getRecord().getZxs().get(1).getTitle());
                        zx_content_1.setText(result.getRecord().getZxs().get(0).getSubtitle());
                        content1 = result.getRecord().getZxs().get(0).getSubtitle();
                        content2 = result.getRecord().getZxs().get(1).getSubtitle();
                        zx_content_2.setText(result.getRecord().getZxs().get(1).getSubtitle());
                        zx1Id = result.getRecord().getZxs().get(0).getId();
                        zx2Id = result.getRecord().getZxs().get(1).getId();
                    }
                    if (result.getRecord().getVipInfo() != null) {
                        phone.setText(result.getRecord().getVipInfo().getPhone());
                        jifen.setText(result.getRecord().getVipInfo().getJifen());
                        continueQd.setText(result.getRecord().getVipInfo().getContinueQd());
                        nickName.setText(result.getRecord().getVipInfo().getNickName());
                        img = result.getRecord().getVipInfo().getHeadImg();
                        utils.display(headImg, result.getRecord().getVipInfo().getHeadImg());
                        if (TextUtils.isEmpty(result.getRecord().getVipInfo().getVipType())) {
                            return;
                        }
                        if (result.getRecord().getVipInfo().getVipType().equals("A")) {
                            diamond1.setVisibility(View.VISIBLE);
                            diamond2.setVisibility(View.VISIBLE);
                            diamond3.setVisibility(View.VISIBLE);
                        } else if (result.getRecord().getVipInfo().getVipType().equals("B")) {
                            diamond1.setVisibility(View.VISIBLE);
                            diamond2.setVisibility(View.VISIBLE);
                        } else if (result.getRecord().getVipInfo().getVipType().equals("C")) {
                            diamond1.setVisibility(View.VISIBLE);
                        }
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
            intent.putExtra("phone", phone.getText().toString());
            intent.putExtra("nickName", nickName.getText().toString());
            intent.putExtra("img", img);
            ((Activity) mContext).startActivityForResult(intent, 1);
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
                            loadData();
                        }
                    }
                }.requestByPost(Constant.URL.QIANDAO, new RequestParams());
            } else {
                CommonUtil.showToastToShort(mContext, "今日已签到");
            }
        } else if (cx_more_lly == v) {
            mainActivity.setDynamicFragment(Constant.FRAGMENT_FLAG_FIND);
        } else if (zx_more_lly == v) {
            mainActivity.setDynamicFragment(Constant.FRAGMENT_FLAG_INFO);
        } else if (market_more_lly == v) {
            mainActivity.setDynamicFragment(Constant.FRAGMENT_FLAG_MARKET);
        } /* else if (zx_iv_1 == v) {
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
        } */ else if (recommend_lin == v) {
            Intent intent = new Intent(mContext, RecommendActivity.class);
            mContext.startActivity(intent);
        } else if (jifenllt == v) {
            Intent intent = new Intent(mContext, UserScoreActivity.class);
            mContext.startActivity(intent);
        } else if (zx1_llt == v) {
            Intent intent = new Intent(mContext, NewsInfoActivity.class);
            intent.putExtra("id", zx1Id);
            intent.putExtra("title", title1);
            intent.putExtra("content", content1);
            startActivity(intent);
        } else if (zx2_llt == v) {
            Intent intent = new Intent(mContext, NewsInfoActivity.class);
            intent.putExtra("id", zx2Id);
            intent.putExtra("title", title2);
            intent.putExtra("content", content2);
            startActivity(intent);
        } else if (trade_llt == v) {
            Intent intent = new Intent(mContext, TradeListActivity.class);
            startActivity(intent);
        } else if (tjkf == v) {
            Intent intent = new Intent(mContext, RecommendKFActivity.class);
            startActivity(intent);
        } else if (obtain_password == v) {
            if (user_et.getText().toString().length() != 0) {
                timeCountUtil = new TimeCountUtil((Activity) mContext, 60000, 1000, obtain_password);
                timeCountUtil.start();
                RequestParams params = new RequestParams();
                params.addQueryStringParameter("user_et", user_et.getText().toString());
                new BaseTask<JsonResult<String>, String>(mContext, R.string.download_notice) {

                    @Override
                    public TypeToken setTypeToken() {
                        return new TypeToken<String>() {
                        };
                    }

                    @Override
                    public void onSuccess() {
                        if (result.isSuccess()) {
                            CommonUtil.showToastToShort(mContext, "密码已通过短信发送至你的手机");
                        } else {
                            CommonUtil.showToastToShort(mContext, result.getMsg());
                        }
                    }
                }.requestByPost(Constant.URL.OBTAIN_PASSWORD, params);
            } else {
                CommonUtil.showToastToShort(mContext, "请输入手机号");
            }
        }
    }

    public void doLogin() {
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
                        updateRedPoint();
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

    public void updateRedPoint() {
        new BaseTask<JsonResult<JSONObject>,String>(mContext){

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>(){};
            }

            @Override
            public void onSuccess() {
                if(result.isSuccess()){
                    Constant.POINTNUM = result.getRecord().optJSONObject("count").optInt("count");
                    mainActivity.bottomPanel.updatePointNum();
                }
            }
        }.requestByPost(Constant.URL.NOTPAIDCOUNT,new RequestParams());
    }
}
