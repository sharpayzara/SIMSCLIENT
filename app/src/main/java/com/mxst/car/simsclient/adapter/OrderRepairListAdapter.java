package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.OrderRepireList;

import java.util.List;

public class OrderRepairListAdapter extends RecyclerView.Adapter<OrderRepairListAdapter.ViewHolder> {
    private List<OrderRepireList.OrderRepire> beans;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public OrderRepairListAdapter(Context context, List<OrderRepireList.OrderRepire> beans) {
        this.beans = beans;
        mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OrderRepairListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_order_repair, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final OrderRepairListAdapter.ViewHolder holder, int position) {
        holder.yyDate.setText(beans.get(position).getYyDate());
        holder.pp.setText(beans.get(position).getPp());
        holder.fixName.setText(beans.get(position).getFixName());
        holder.license.setText(beans.get(position).getLicense());
        holder.wxlxName.setText(beans.get(position).getWxlxName());
        holder.handmanName.setText(beans.get(position).getHandmanName());
        holder.lx.setText(beans.get(position).getLx());
        if(beans.get(position).getLx().equals("已预约")){
            holder.lx_rlt.setBackgroundColor(mContext.getResources().getColor(R.color.order_repire_green));
        }else{
            holder.lx_rlt.setBackgroundColor(mContext.getResources().getColor(R.color.order_repire_duck));
        }

    }

    public int getItemCount() {
        return beans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout lx_rlt;
        private TextView lx,yyDate,pp,fixName,license,wxlxName,handmanName;

        public ViewHolder(View itemView) {
            super(itemView);
            lx = (TextView) itemView.findViewById(R.id.lx);
            lx_rlt = (RelativeLayout) itemView.findViewById(R.id.lx_rlt);
            yyDate = (TextView) itemView.findViewById(R.id.yyDate);
            pp = (TextView) itemView.findViewById(R.id.pp);
            fixName = (TextView) itemView.findViewById(R.id.fixName);
            license = (TextView) itemView.findViewById(R.id.license);
            wxlxName = (TextView) itemView.findViewById(R.id.wxlxName);
            handmanName = (TextView) itemView.findViewById(R.id.handmanName);
        }
    }
}
