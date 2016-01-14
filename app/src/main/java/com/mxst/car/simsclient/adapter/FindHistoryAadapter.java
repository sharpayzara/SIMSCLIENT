package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.CarList;

import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class FindHistoryAadapter extends RecyclerView.Adapter<FindHistoryAadapter.ViewHolder> {
    private List<CarList.CarsEntity> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public FindHistoryAadapter(Context context, List<CarList.CarsEntity> bean) {
        this.bean = bean;
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FindHistoryAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_find_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.pzTv.setText(bean.get(position).getLicense());  //牌照
        holder.ppTv.setText(bean.get(position).getPp());     //品牌
        holder.cxTv.setText(bean.get(position).getCx());   //车型

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
    }

    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pzTv, ppTv, cxTv;


        public ViewHolder(View itemView) {
            super(itemView);
            pzTv = (TextView) itemView.findViewById(R.id.item_history_pz);
            ppTv = (TextView) itemView.findViewById(R.id.item_history_pp);
            cxTv = (TextView) itemView.findViewById(R.id.item_history_cx);

        }
    }
}

