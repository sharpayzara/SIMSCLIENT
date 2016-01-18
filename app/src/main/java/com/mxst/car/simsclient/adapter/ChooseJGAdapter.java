package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.JGList;
import com.mxst.car.simsclient.utils.CommonUtil;

import java.util.List;

public class ChooseJGAdapter extends RecyclerView.Adapter<ChooseJGAdapter.ViewHolder> {
    private List<JGList.JG> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ChooseJGAdapter(Context context, List<JGList.JG> bean) {
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

    public ChooseJGAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_jg_choose, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameTv.setText(bean.get(position).getName());
        holder.phoneTv.setText(bean.get(position).getPhone());
        holder.starLevelTv.setRating(Float.parseFloat(bean.get(position).getStar_level()));
        BitmapUtils bitmapUtils = CommonUtil.getBitMapUtils(mContext);
        bitmapUtils.display(holder.imgIv, bean.get(position).getHeadPortrait());
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
        private TextView nameTv, phoneTv;
        private ImageView imgIv;
        private RatingBar starLevelTv;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.name);
            phoneTv = (TextView) itemView.findViewById(R.id.phone);
            starLevelTv = (RatingBar) itemView.findViewById(R.id.star_level);
            imgIv = (ImageView) itemView.findViewById(R.id.img_iv);

        }
    }
}

