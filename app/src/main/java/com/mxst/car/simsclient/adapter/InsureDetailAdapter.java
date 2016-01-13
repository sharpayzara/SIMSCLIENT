package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.InsureDetailList;

import java.util.List;

public class InsureDetailAdapter extends RecyclerView.Adapter<InsureDetailAdapter.ViewHolder> {
    private List<InsureDetailList.InsureDetail> beans;
    private LayoutInflater layoutInflater;

    public InsureDetailAdapter(Context context, List<InsureDetailList.InsureDetail> beans) {
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

    public InsureDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_insure_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final InsureDetailAdapter.ViewHolder holder, final int position) {
        holder.sxeDate.setText(beans.get(position).getSxeDate());
        holder.sxbDate.setText(beans.get(position).getSxbDate());
        holder.name.setText(beans.get(position).getName());
        holder.currHjsj.setText("￥" + beans.get(position).getCurrHjsj());
        holder.insure_item_llt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(holder.insure_item_llt,position);
                }
            }
        });
    }

    public int getItemCount() {
        return beans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sxeDate,sxbDate,name,currHjsj;
        private LinearLayout insure_item_llt;

        public ViewHolder(View itemView) {
            super(itemView);
            sxeDate = (TextView) itemView.findViewById(R.id.sxeDate);
            sxbDate = (TextView) itemView.findViewById(R.id.sxbDate);
            name = (TextView) itemView.findViewById(R.id.name);
            currHjsj = (TextView) itemView.findViewById(R.id.currHjsj);
            insure_item_llt = (LinearLayout) itemView.findViewById(R.id.insure_item_llt);
        }
    }
}
