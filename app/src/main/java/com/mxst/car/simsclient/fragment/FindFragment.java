package com.mxst.car.simsclient.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.BrandFindActivity;
import com.mxst.car.simsclient.adapter.PersonAdapter;
import com.mxst.car.simsclient.business.BaseTask;
import com.mxst.car.simsclient.business.JsonResult;
import com.mxst.car.simsclient.entity.BrandList;
import com.mxst.car.simsclient.entity.Person;
import com.mxst.car.simsclient.layout.QuickindexBar;
import com.mxst.car.simsclient.utils.Constant;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FindFragment extends Fragment implements View.OnClickListener {
    LayoutInflater inflater;
    Context mContext;
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
        brandList();
        return root;
    }

    private void brandList() {
        new BaseTask<JsonResult<JSONObject>, String>(getActivity(), "加载中") {

            @Override
            public TypeToken setTypeToken() {
                return new TypeToken<JSONObject>() {
                };
            }

            @Override
            public void onSuccess() {
                if (result.isSuccess()) {
                    persons.clear();
                    List<BrandList> tempList = new Gson().fromJson(result.getRecord().optString("brandList"),
                            new TypeToken<List<BrandList>>() {
                            }.getType());
                    List<Person> rmBrands = new Gson().fromJson(result.getRecord().optString("rmBrands"),
                            new TypeToken<List<Person>>() {
                            }.getType());
                    for (int i = 0; i < rmBrands.size(); i++) {
                        Person person = new Person();
                        person.setPinyin("★");
                        person.setBrand(rmBrands.get(i).getBrand());
                        person.setPath(rmBrands.get(i).getPath());
                        persons.add(person);
                    }
                    Iterator<BrandList> it = tempList.iterator();
                    while (it.hasNext()) {
                        BrandList entity = it.next();
                        String py = entity.getLetter();
                        List<Person> data = entity.getData();
                        for (int i = 0; i < data.size(); i++) {
                            Person person = new Person();
                            person.setPinyin(py);
                            person.setBrand(data.get(i).getBrand());
                            person.setPath(data.get(i).getPath());
                            persons.add(person);
                        }

                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }.requestByPost(Constant.URL.BRANDLIST, null);
    }

    private void initUI() {
        slideBar = (QuickindexBar) root.findViewById(R.id.find_slideBar);
        lv = (ListView) root.findViewById(R.id.find_list);
        tv_zimu = (TextView) root.findViewById(R.id.find_zimu);
        handler = new Handler();
        persons = new ArrayList<Person>();

        Collections.sort(persons);
        adapter = new PersonAdapter(persons, getActivity());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BrandFindActivity.class);
                intent.putExtra("brand", persons.get(position).getBrand());
                startActivity(intent);
            }
        });
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
