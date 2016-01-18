package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.ScoreList;

import java.util.List;

public class ScorelistAdapter extends RecyclerView.Adapter<ScorelistAdapter.ViewHolder> {
    private List<ScoreList.Score> bean;
    private LayoutInflater layoutInflater;
    private BitmapUtils utils;
    private Context mContext;

    public ScorelistAdapter(Context context, List<ScoreList.Score> bean) {
        this.bean = bean;
        mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        utils = new BitmapUtils(context);
        utils.configDefaultLoadFailedImage(R.drawable.plugin_img);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ScorelistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_score, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ScorelistAdapter.ViewHolder holder, int position) {
        holder.jfChange.setText(bean.get(position).getJfChange());
        // holder.img_iv.setText(bean.get(position).);
        // holder.jfxw_tv.setText(bean.get(position).getJfxw());
        if(bean.get(position).getJfxw().equals("注册")){
            holder.img_iv.setImageResource(R.drawable.zhuce);
        }else if(bean.get(position).getJfxw().equals("签到")){
            holder.img_iv.setImageResource(R.drawable.qiandao);
        }else if(bean.get(position).getJfxw().equals("推荐有效客户")){
            holder.img_iv.setImageResource(R.drawable.tuijian);
        }else if(bean.get(position).getJfxw().equals("购买保险")){
            holder.img_iv.setImageResource(R.drawable.goumai);
        }else if(bean.get(position).getJfxw().equals("推荐保险")){
            holder.img_iv.setImageResource(R.drawable.baoxian);
        }else if(bean.get(position).getJfxw().equals("会员购车")){
            holder.img_iv.setImageResource(R.drawable.gouche);
        }else if(bean.get(position).getJfxw().equals("推荐成交")){
            holder.img_iv.setImageResource(R.drawable.chengjiao);
        }else if(bean.get(position).getJfxw().equals("维修")){
            holder.img_iv.setImageResource(R.drawable.weixio);
        }else if(bean.get(position).getJfxw().equals("商城消费")){
            holder.img_iv.setImageResource(R.drawable.shangchen);
        }
        holder.produceDate.setText(bean.get(position).getProduceDate());
    }

    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout item_rlt;
        private TextView produceDate,jfChange;
        private ImageView img_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_rlt = (RelativeLayout) itemView.findViewById(R.id.item_rlt);
            img_iv = (ImageView) itemView.findViewById(R.id.img_iv);
            produceDate = (TextView) itemView.findViewById(R.id.produceDate);
            jfChange = (TextView) itemView.findViewById(R.id.jfChange);
        }
    }
}
