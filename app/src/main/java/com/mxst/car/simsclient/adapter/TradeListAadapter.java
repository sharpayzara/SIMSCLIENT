package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.TradeList;

import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class TradeListAadapter extends RecyclerView.Adapter<TradeListAadapter.ViewHolder> {
    private List<TradeList.Trade> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public TradeListAadapter(Context context, List<TradeList.Trade> bean) {
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

    public TradeListAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_trade , parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(bean.get(position).getName());
        holder.title.setText(bean.get(position).getBrand()+bean.get(position).getNiankuan()+" "+
                bean.get(position).getXinghao() +" "+bean.get(position).getKuanxing());
        holder.phone.setText(bean.get(position).getPhone());
        holder.buyDate.setText(bean.get(position).getBuyDate());
        holder.trade_llt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.trade_llt,position);
            }
        });

    }

    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,title,phone,buyDate;
        private LinearLayout trade_llt;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            name.getPaint().setFakeBoldText(true);
            title = (TextView) itemView.findViewById(R.id.title);
            phone = (TextView) itemView.findViewById(R.id.phone);
            buyDate = (TextView) itemView.findViewById(R.id.buyDate);
            trade_llt = (LinearLayout) itemView.findViewById(R.id.trade_llt);
        }
    }
}

