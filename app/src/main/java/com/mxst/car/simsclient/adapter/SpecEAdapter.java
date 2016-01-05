package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.SpecList;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/5.
 * version:  V1.0
 * Description:
 */
public class SpecEAdapter extends BaseExpandableListAdapter {
    private List<SpecList> bean;
    private Context context;

    public SpecEAdapter(Context context, List<SpecList> bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getGroupCount() {
        return bean.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return bean.get(groupPosition).getXhs().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return bean.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return bean.get(groupPosition).getXhs().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_brandfind_group, null);
            groupHolder = new GroupHolder();
            groupHolder.specTv = (TextView) convertView.findViewById(R.id.item_brand_spec_tv);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.specTv.setText(bean.get(groupPosition).getSpec());
        return convertView;

    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_brandfind, null);
            childHolder = new ChildHolder();
            childHolder.xinghaoTv = (TextView) convertView.findViewById(R.id.item_brand_xinghao_tv);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        if (bean.get(groupPosition).getXhs().size() > 0) {
            childHolder.xinghaoTv.setText(bean.get(groupPosition).getXhs().get(childPosition).getXingHao());
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView specTv;

    }

    class ChildHolder {
        public TextView xinghaoTv;

    }

}
