package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.fragment.Person;

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

    public PersonAdapter(ArrayList<Person> person, Context context) {
        this.persons = person;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
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
            holder.img = (ImageView) convertView.findViewById(R.id.item_find_img);
            bitmapUtils.configDefaultLoadFailedImage(R.drawable.plugin_img);
            bitmapUtils.display(holder.img, persons.get(position).getPath());
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String string = null;

        if (position == 0) {
            string = persons.get(position).getPinyin().substring(0, 1);
        } else {
            String py = persons.get(position).getPinyin().substring(0, 1);
            String spy = persons.get(position - 1).getPinyin().substring(0, 1);
            if (!py.equals(spy)) {
                string = persons.get(position).getPinyin().substring(0, 1);
            }
        }
        if (string == null) {
            holder.tv_py.setVisibility(View.GONE);
        } else {
            holder.tv_py.setVisibility(View.VISIBLE);
            holder.tv_py.setText(string);
        }
        holder.tv_name.setText(persons.get(position).getBrand());
        return convertView;
    }
}

class ViewHolder {
    TextView tv_py, tv_name;
    ImageView img;

}

