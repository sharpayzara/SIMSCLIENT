package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.EvaluateActivity;
import com.mxst.car.simsclient.activity.InsureListActivity;
import com.mxst.car.simsclient.activity.FindRepairActivity;
import com.mxst.car.simsclient.activity.OrderRepairActivity;
import com.mxst.car.simsclient.adapter.ImageAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.AdvertisementList;
import com.mxst.car.simsclient.entity.EvaluateList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RepairFragment extends Fragment implements View.OnClickListener {
    private View root;
    LayoutInflater inflater;
    Context mContext;
    RelativeLayout order_repair_rlt, query_process_rlt, query_history_rlt, contact_builder_rlt, query_problem;
    ViewPager advPager = null;
    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    int imgFlag = 0;
    ImageAdapter adapter;
    List<String> listUrls = new ArrayList<>();
    List<View> listViews = new ArrayList();
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_repair, container, false);
        mContext = getActivity();
        initUI();
        initData();
        this.inflater = inflater;
        return root;
    }

    private void initData() {
        loadData();
        loadEvaluateData();
    }

    private void loadEvaluateData() {
        Log.e("eee", Constant.AUTHENTICATION_TOKEN + "");
        new BaseTask<JsonResult<EvaluateList>, String>(mContext) {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<EvaluateList>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    if (result.getRecord().getEvaluateList().size() > 0) {
                        Intent intent = new Intent(mContext, EvaluateActivity.class);

                        startActivity(intent);
                    }
                }
            }
        }.requestByPost(Constant.URL.ARTISANCOMMONT, new RequestParams());
    }

    private void loadData() {
        new BaseTask<JsonResult<AdvertisementList>, String>(mContext, R.string.download_notice) {
            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<AdvertisementList>(){};
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    listUrls.clear();
                    listViews.clear();
                    List<AdvertisementList.Advertisement> tempList = result.getRecord().getIndex();
                    imgFlag = tempList.size();
                    for (AdvertisementList.Advertisement adv : tempList) {
                        listUrls.add(adv.getAdImg());
                        listViews.add(new ImageView(mContext));
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }.requestByPost(Constant.URL.LOAD_SERVICE, new RequestParams());
    }

    private void initUI() {
        order_repair_rlt = (RelativeLayout) root.findViewById(R.id.order_repair_rlt);
        query_process_rlt = (RelativeLayout) root.findViewById(R.id.query_process_rlt);
        query_history_rlt = (RelativeLayout) root.findViewById(R.id.query_history_rlt);
        contact_builder_rlt = (RelativeLayout) root.findViewById(R.id.contact_builder_rlt);
        query_problem = (RelativeLayout) root.findViewById(R.id.query_problem);
        advPager = (ViewPager) root.findViewById(R.id.list_view);
        List<View> advPics = new ArrayList<View>();
        order_repair_rlt.setOnClickListener(this);
        query_process_rlt.setOnClickListener(this);
        query_history_rlt.setOnClickListener(this);
        contact_builder_rlt.setOnClickListener(this);
        query_problem.setOnClickListener(this);
        adapter = new ImageAdapter(mContext, listViews, listUrls);
        advPager.setAdapter(adapter);
        advPager.setOnPageChangeListener(new GuidePageChangeListener());
        advPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();
                    }
                }
            }

        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_repair_rlt:
                Intent intent = new Intent(mContext, OrderRepairActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.query_process_rlt:
                Intent i = new Intent(mContext, FindRepairActivity.class);
                mContext.startActivity(i);
                break;
            case R.id.query_history_rlt:

                break;
            case R.id.contact_builder_rlt:

                break;
            case R.id.query_problem:
                Intent it_qp = new Intent(mContext, InsureListActivity.class);
                mContext.startActivity(it_qp);
                break;
        }
    }


    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            what.getAndSet(arg0);
           /* for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.drawable.banner_dian_focus);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.drawable.banner_dian_blur);
                }
            }*/

        }
    }

    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > listViews.size() - 1) {
            what.getAndAdd(-imgFlag);
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
    }

    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            advPager.setCurrentItem(msg.what);
            super.handleMessage(msg);
        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Constant.REQUESTCODE.LOGINBACK){
            initData();
        }
    }
}
