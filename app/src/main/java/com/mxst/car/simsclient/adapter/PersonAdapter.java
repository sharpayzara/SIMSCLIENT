package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.Person;

import java.util.ArrayList;

/**
 * author   Joy
 * Date:  2016/1/4.
 * version:  V1.0
 * Description:
 */
public class PersonAdapter extends BaseAdapter {
    private ArrayList<Person> persons;
    private LayoutInflater layoutInflater;
    private Context mContext;
    BitmapUtils bitmapUtils;

    public PersonAdapter(ArrayList<Person> person, Context context) {
        this.persons = person;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
        bitmapUtils = new BitmapUtils(mContext);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.plugin_img);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            BitmapUtils bitmapUtils = new BitmapUtils(mContext);
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_find, null);
            holder.tv_py = (TextView) convertView.findViewById(R.id.item_find_pingyin);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_find_brand);
            holder.lin = (LinearLayout) convertView.findViewById(R.id.item_find_lin);
            holder.img = (ImageView) convertView.findViewById(R.id.item_find_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String string = null;

//        if ("★".equals(persons.get(position).getPinyin())) {
//            holder.tv_py.setVisibility(View.GONE);
//        }

        if (position == 0) {
            string = "常用品牌";
        } else {
            String py = persons.get(position).getPinyin();
            String spy = persons.get(position - 1).getPinyin();
            if (!py.equals(spy)) {
                string = persons.get(position).getPinyin();
            }
        }
        if (string == null) {
            holder.lin.setVisibility(View.GONE);
        } else {
            holder.lin.setVisibility(View.VISIBLE);
            holder.tv_py.setText(string);
        }
        bitmapUtils.display(holder.img, persons.get(position).getPath());
        holder.tv_name.setText(persons.get(position).getBrand());
        return convertView;
    }

    public final class ViewHolder {
        LinearLayout lin;
        TextView tv_py, tv_name;
        ImageView img;

    }
}



