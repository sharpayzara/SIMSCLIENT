package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.InsureList;

import java.util.List;

public class InsureListAdapter extends RecyclerView.Adapter<InsureListAdapter.ViewHolder> {
    private List<InsureList.Insure> beans;
    private LayoutInflater layoutInflater;

    public InsureListAdapter(Context context, List<InsureList.Insure> beans) {
        this.beans = beans;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public InsureListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_insure, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final InsureListAdapter.ViewHolder holder, final int position) {
        holder.pp.setText(beans.get(position).getPp());
        holder.brandNo.setText(beans.get(position).getBrandNo());
        holder.license.setText(beans.get(position).getLicense());
        holder.insureLlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(holder.insureLlt,position);
                }
            }
        });
    }

    public int getItemCount() {
        return beans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pp,brandNo,license;
        private LinearLayout insureLlt;

        public ViewHolder(View itemView) {
            super(itemView);
            pp = (TextView) itemView.findViewById(R.id.pp);
            brandNo = (TextView) itemView.findViewById(R.id.brandNo);
            license = (TextView) itemView.findViewById(R.id.license);
            insureLlt = (LinearLayout) itemView.findViewById(R.id.insure_llt);
        }
    }
}
