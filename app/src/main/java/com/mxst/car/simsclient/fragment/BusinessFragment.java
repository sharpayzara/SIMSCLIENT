package com.mxst.car.simsclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.FixDetailActivity;
import com.mxst.car.simsclient.adapter.BusinessListAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.BusinessList;
import com.mxst.car.simsclient.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class BusinessFragment extends Fragment {
    private Context mContext;
    private View root;
    private RecyclerView mRecyclerView;
    private MaterialRefreshLayout materialRefreshLayout;
    private BusinessListAdapter mAdapter;
    private List<BusinessList.BusinessEntity> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = this.getActivity();
        root  = inflater.inflate(R.layout.fragment_market_business, null);
        initUI();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }


    private void initUI() {
        list = new ArrayList<>();
        materialRefreshLayout = (MaterialRefreshLayout) root.findViewById(R.id.materialRefreshLayout);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle_view);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                loadData();
                materialRefreshLayout.finishRefresh();
            }
        });
        mAdapter =new BusinessListAdapter(mContext,list,true);

        mAdapter.setOnItemClickListener(new BusinessListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext,FixDetailActivity.class);
                intent.putExtra("loginNo",list.get(position).getLoginNo());
               // startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void loadData() {
       new BaseTask<JsonResult<BusinessList>,String>(mContext,R.string.download_notice){

           @Override
           public TypeToken setTypeToken() {
               return new TypeToken<BusinessList>(){};
           }

           @Override
           public void onSuccess() {
               list.clear();
                if(result.isSuccess()){
                    list.addAll(result.getRecord().getCount().getNotPaidList());
                }
               mAdapter.notifyDataSetChanged();
           }
       }.requestByPost(Constant.URL.WXLIST,new RequestParams());
    }

}
