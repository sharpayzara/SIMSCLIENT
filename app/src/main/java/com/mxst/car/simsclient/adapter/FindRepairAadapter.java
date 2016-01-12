package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.WxjcList;

import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class FindRepairAadapter extends RecyclerView.Adapter<FindRepairAadapter.ViewHolder> {
    private List<WxjcList.WxjcListEntity> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public FindRepairAadapter(Context context, List<WxjcList.WxjcListEntity> bean) {
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

    public FindRepairAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_man_choose, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameTv.setText(bean.get(position).getLicense());
        holder.typeTv.setText(bean.get(position).getWxlx());
        holder.storeTv.setText(bean.get(position).getFixName());
        holder.startTv.setText(bean.get(position).getNoteDate());
        holder.endTv.setText(bean.get(position).getOverDate());
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
        private TextView nameTv, typeTv, storeTv, startTv, endTv;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.item_fix_name);
            typeTv = (TextView) itemView.findViewById(R.id.item_fix_type);
            storeTv = (TextView) itemView.findViewById(R.id.item_fix_store);
            startTv = (TextView) itemView.findViewById(R.id.item_fix_startime);
            endTv = (TextView) itemView.findViewById(R.id.item_fix_endtime);
        }
    }
}

