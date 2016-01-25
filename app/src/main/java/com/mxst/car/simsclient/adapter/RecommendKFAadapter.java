package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.FindCusts;

import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class RecommendKFAadapter extends RecyclerView.Adapter<RecommendKFAadapter.ViewHolder> {
    private List<FindCusts.CustsEntity> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public RecommendKFAadapter(Context context, List<FindCusts.CustsEntity> bean) {
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

    public RecommendKFAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_com_kf, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameTv.setText(bean.get(position).getName());
        holder.typeTv.setText(bean.get(position).getEffective());
        holder.dateTv.setText(bean.get(position).getTjDate());
        holder.phoneTv.setText(bean.get(position).getPhone());
        if (!bean.get(position).getEffective().equals("有效")) {
            holder.bgTv.setBackgroundResource(R.drawable.return2);
        }else {
            holder.typeTv.setTextColor(mContext.getResources().getColor(R.color.title_orange));
            holder.bgTv.setBackgroundResource(R.drawable.return1);
        }

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
        private TextView nameTv, typeTv, phoneTv, bgTv, dateTv;


        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.com_kf_name);
            typeTv = (TextView) itemView.findViewById(R.id.com_kf_type);
            phoneTv = (TextView) itemView.findViewById(R.id.com_kf_phone);
            bgTv = (TextView) itemView.findViewById(R.id.com_kf_bg);
            dateTv = (TextView) itemView.findViewById(R.id.com_kf_date);
        }
    }
}

