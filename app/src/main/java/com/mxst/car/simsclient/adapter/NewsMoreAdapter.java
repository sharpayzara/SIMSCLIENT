package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.NewsMoreList;

import java.util.List;

public class NewsMoreAdapter extends RecyclerView.Adapter<NewsMoreAdapter.ViewHolder> {
    private List<NewsMoreList.NewsMore> bean;
    private LayoutInflater layoutInflater;

    public NewsMoreAdapter(Context context, List<NewsMoreList.NewsMore> bean) {
        this.bean = bean;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public NewsMoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_zx, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewsMoreAdapter.ViewHolder holder, int position) {
        holder.title.setText(bean.get(position).getTitle());
        holder.subtitle.setText(bean.get(position).getSubtitle());
        holder.releaseTime.setText(bean.get(position).getReleaseTime().substring(0,10));
        holder.dianjishu.setText(bean.get(position).getDianjishu()+"");
        /*if (bean.get(position).getGuidePrice() == 0) {
            holder.itemResGuideprice.setText("指导价:" + "暂无");
        } else {
            holder.itemResGuideprice.setText("指导价:" + bean.get(position).getGuidePrice());
        }*/
    }

    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,subtitle,releaseTime,dianjishu;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            releaseTime = (TextView) itemView.findViewById(R.id.releaseTime);
            dianjishu = (TextView) itemView.findViewById(R.id.dianjishu);
            img = (ImageView) itemView.findViewById(R.id.img);

        }
    }
}
