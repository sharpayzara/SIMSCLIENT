package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.adapter.PersonAdapter;
import com.mxst.car.simsclient.layout.QuickindexBar;

import java.util.ArrayList;
import java.util.Collections;

public class FindFragment extends Fragment implements View.OnClickListener {
    LayoutInflater inflater;
    RelativeLayout adLayout;
    Context mContext;
    LinearLayout adcolumnLayout;
    RelativeLayout dmf_layout;
    private QuickindexBar slideBar;
    private ListView lv;
    private ArrayList<Person> persons;
    private TextView tv_zimu;
    private Handler handler;
    private PersonAdapter adapter;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_find, container, false);
        mContext = getActivity();
        this.inflater = inflater;
        initUI();
        return root;
    }

    private void initUI() {
        slideBar = (QuickindexBar) root.findViewById(R.id.find_slideBar);
        lv = (ListView) root.findViewById(R.id.find_list);
        tv_zimu = (TextView) root.findViewById(R.id.find_zimu);
        handler = new Handler();
        persons = new ArrayList<Person>();
        persons.add(new Person("http://222.177.210.200/mxqc/brand/1448014403999.png", "撒打算的"));
        persons.add(new Person("http://222.177.210.200/mxqc/brand/1448014403999.png", "飒沓"));
        persons.add(new Person("http://222.177.210.200/mxqc/brand/1448014403999.png", "请问哦千万i恶婆"));
        persons.add(new Person("http://222.177.210.200/mxqc/brand/1448014403999.png", "林俊杰"));
        persons.add(new Person("http://222.177.210.200/mxqc/brand/1448014403999.png", "周杰伦"));

        Collections.sort(persons);
        adapter = new PersonAdapter(persons, getActivity());
        lv.setAdapter(adapter);

        slideBar.setOnSlideTouchListener(new QuickindexBar.OnSlideTouchListener() {

            @Override
            public void onBack(String str) {

                showZimu(str);

                for (int i = 0; i < persons.size(); i++) {
                    if (persons.get(i).getPinyin().substring(0, 1).equals(str)) {
                        lv.setSelection(i);
                        break;

                    }
                }
            }
        });


    }


    @Override
    public void onClick(View v) {

    }

    private void showZimu(String string) {

        tv_zimu.setVisibility(View.VISIBLE);
        tv_zimu.setText(string);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                tv_zimu.setVisibility(View.GONE);
            }
        }, 1500);
    }

}
