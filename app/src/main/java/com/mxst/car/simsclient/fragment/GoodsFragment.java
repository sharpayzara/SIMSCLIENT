package com.mxst.car.simsclient.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mxst.car.simsclient.R;

public class GoodsFragment extends Fragment {
    private Context mContext;
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = this.getActivity();
        root  = inflater.inflate(R.layout.fragment_market_not_open, null);
        return root;
    }

}
