package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.WxList;

import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class FindWxAadapter extends RecyclerView.Adapter<FindWxAadapter.ViewHolder> {
    private List<WxList.WxlsListEntity> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public FindWxAadapter(Context context, List<WxList.WxlsListEntity> bean) {
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

    public FindWxAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_find_wx, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.numTv.setText(bean.get(position).getLoginNo());
        holder.dataTv.setText(bean.get(position).getNoteDate());
        holder.typeTv.setText(bean.get(position).getWxlx());
        holder.jgTv.setText("￥"+bean.get(position).getSjcurr());
        holder.glTv.setText(bean.get(position).getGls());
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
        private TextView numTv, dataTv, typeTv, jgTv, glTv;


        public ViewHolder(View itemView) {
            super(itemView);
            numTv = (TextView) itemView.findViewById(R.id.item_wx_num);
            dataTv = (TextView) itemView.findViewById(R.id.item_wx_data);
            typeTv = (TextView) itemView.findViewById(R.id.item_wx_wxlx);
            jgTv = (TextView) itemView.findViewById(R.id.item_wx_sjcurr);
            glTv = (TextView) itemView.findViewById(R.id.item_wx_gls);

        }
    }
}

