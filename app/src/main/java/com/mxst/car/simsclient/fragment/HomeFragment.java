package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mxst.car.simsclient.R;

public class HomeFragment extends Fragment implements View.OnClickListener{
	LayoutInflater inflater;
	RelativeLayout adLayout;
	Context mContext;
	LinearLayout adcolumnLayout;
	RelativeLayout dmf_layout;

	private View root;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root =  inflater.inflate(R.layout.fragment_home, container, false);
		//adcolumnLayout = (LinearLayout) root.findViewById(R.id.ad_column);
		mContext = getActivity();
		this.inflater = inflater;
	//	initUI();
		return root;
	}

	private void initUI() {
		//dmf_layout = (RelativeLayout) root.findViewById(R.id.dmf_layout);
        dmf_layout.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {

	}
}
