package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.IndexList;
import com.mxst.car.simsclient.entity.SpecList;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/5.
 * version:  V1.0
 * Description:
 */
public class SpecAdapter extends RecyclerView.Adapter<SpecAdapter.ViewHolder> {
    private List<SpecList> bean;
    private LayoutInflater layoutInflater;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SpecAdapter(List<IndexList> bean, LayoutInflater layoutInflater, Context mContext) {
     //   this.bean = bean;
        layoutInflater = LayoutInflater.from(mContext);



    }

    public SpecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SpecAdapter.ViewHolder holder, int position) {
        String spce = null;
        if (position == 0) {
            holder.sepcTv.setText(bean.get(position).getSpec());
        } else {
            String sp = bean.get(position).getSpec();
            String nsp = bean.get(position - 1).getSpec();
            if (!sp.equals(nsp)) {
                spce = bean.get(position).getSpec();
            }
        }
        if (spce == null) {
            holder.sepcTv.setVisibility(View.GONE);
        } else {
            holder.sepcTv.setVisibility(View.VISIBLE);
            holder.sepcTv.setText(spce);

        }
//        holder.xinghaoTv.setText(bean.get(position).getXhs().);
    }


    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sepcTv, xinghaoTv;


        public ViewHolder(View itemView) {
            super(itemView);
            sepcTv = (TextView) itemView.findViewById(R.id.item_brand_spec_tv);
            xinghaoTv = (TextView) itemView.findViewById(R.id.item_brand_xinghao_tv);
        }
    }
}
