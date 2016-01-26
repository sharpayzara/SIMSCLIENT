package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.IndexList;

import java.util.List;

/**
 * author   Joy
 * Date:  2015/12/3.
 * version:  V1.0
 * Description:
 */
public class NewsCentreAdapter extends RecyclerView.Adapter<NewsCentreAdapter.ViewHolder> {
    private List<IndexList> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public NewsCentreAdapter(Context context, List<IndexList> bean) {
        this.bean = bean;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsCentreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewsCentreAdapter.ViewHolder holder, final int position) {
        BitmapUtils bitmapUtils = new BitmapUtils(mContext);
        holder.titleTv.setText(bean.get(position).getTitle());
        holder.subtitle.setText(bean.get(position).getSubtitle());
        holder.timeTv.setText(bean.get(position).getInterval_str());
        holder.dianjiTv.setText(bean.get(position).getDianjishu() + "人点击");
        holder.typeTv.setText(bean.get(position).getCategory());
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.plugin_img);
        bitmapUtils.display(holder.img, bean.get(position).getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    holder.dianjiTv.setText((bean.get(position).getDianjishu() + 1) + "人点击");
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv, subtitle, timeTv, dianjiTv, typeTv;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_news_title);
            subtitle = (TextView) itemView.findViewById(R.id.item_news_info);
            timeTv = (TextView) itemView.findViewById(R.id.item_news_time);
            dianjiTv = (TextView) itemView.findViewById(R.id.item_news_click);
            typeTv = (TextView) itemView.findViewById(R.id.item_news_sort);
            img = (ImageView) itemView.findViewById(R.id.item_news_img);
        }
    }
}
