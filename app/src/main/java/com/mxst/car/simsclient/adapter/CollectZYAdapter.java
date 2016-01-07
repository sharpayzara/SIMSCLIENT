package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.activity.ViewImageActivity;
import com.mxst.car.simsclient.entity.CollectZYList;

import java.util.ArrayList;
import java.util.List;

public class CollectZYAdapter extends RecyclerView.Adapter<CollectZYAdapter.ViewHolder> {
    private List<CollectZYList.ZYEntity> bean;
    private LayoutInflater layoutInflater;
    private BitmapUtils utils;
    private Context mContext;

    public CollectZYAdapter(Context context, List<CollectZYList.ZYEntity> bean) {
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

    public CollectZYAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_zy, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CollectZYAdapter.ViewHolder holder, int position) {
        holder.content_tv.setText(bean.get(position).getBrand()+"  "+bean.get(position).getMj()
        +"  "+bean.get(position).getVehicleXinghao()  +"  "+bean.get(position).getKx());
        if(bean.get(position).getColors() != null && bean.get(position).getColors().size() > 0){
            holder.outColorName.setText(bean.get(position).getColors().get(0).getOutColorName() + "万");
        }else{
            holder.outColorName.setText("未知");
        }
        holder.zj_tv.setText(bean.get(position).getZj());
        holder.commit_date.setText(bean.get(position).getCommit_date());
       // utils.display(holder.img,bean.get(position).getImg());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewImageActivity.class);
                ArrayList<Bitmap> tempList = new ArrayList();
                tempList.add(holder.img.getDrawingCache());
                intent.putParcelableArrayListExtra("imgList", tempList);
                mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content_tv,outColorName,zj_tv,commit_date;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            content_tv = (TextView) itemView.findViewById(R.id.content_tv);
            outColorName = (TextView) itemView.findViewById(R.id.outColorName);
            zj_tv = (TextView) itemView.findViewById(R.id.zj_tv);
            commit_date = (TextView) itemView.findViewById(R.id.commit_date);
            img = (ImageView) itemView.findViewById(R.id.img);
            img.setDrawingCacheEnabled(true);
        }
    }
}
