package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.ParaResult;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/7.
 * version:  V1.0
 * Description:
 */
public class CarDetailAdapter extends BaseAdapter {
    private List<ParaResult.ResourceDetail.ParaListEntity> bean;
    private LayoutInflater layoutInflater;

    public CarDetailAdapter(Context context, List<ParaResult.ResourceDetail.ParaListEntity> paraList) {
        this.bean = paraList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoloder viewHoloder = null;
        if (convertView == null) {
            viewHoloder = new ViewHoloder();
            convertView = layoutInflater.inflate(R.layout.item_carmore, null);
            viewHoloder.nameTv = (TextView) convertView.findViewById(R.id.item_more_name);
            viewHoloder.valueTv = (TextView) convertView.findViewById(R.id.item_more_value);
            convertView.setTag(viewHoloder);
        } else {

            viewHoloder = (ViewHoloder) convertView.getTag();
        }
        viewHoloder.nameTv.setText(bean.get(position).getParaName());
        viewHoloder.valueTv.setText(bean.get(position).getParaVal());
        return convertView;
    }

    public final class ViewHoloder {
        TextView nameTv, valueTv;
    }
}