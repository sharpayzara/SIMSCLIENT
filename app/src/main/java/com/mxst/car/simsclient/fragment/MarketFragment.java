package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mxst.car.simsclient.R;

public class MarketFragment extends Fragment implements View.OnClickListener {
    private View root;
    LayoutInflater inflater;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_market, container, false);
        mContext = getActivity();
        this.inflater = inflater;
        return root;
    }

    @Override
    public void onClick(View v) {

    }
}
