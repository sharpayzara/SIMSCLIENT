package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.CollectZXList;
import com.mxst.car.simsclient.utils.TimeUtils;

import java.util.List;

public class CollectZXAdapter extends RecyclerView.Adapter<CollectZXAdapter.ViewHolder> {
    private List<CollectZXList.ZXEntity> bean;
    private LayoutInflater layoutInflater;
    private BitmapUtils utils;
    private Context mContext;

    public CollectZXAdapter(Context context, List<CollectZXList.ZXEntity> bean) {
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

    public CollectZXAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_zx, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CollectZXAdapter.ViewHolder holder, final int position) {
        holder.title.setText(bean.get(position).getTitle());
        holder.subtitle.setText(bean.get(position).getTitle());
        String date = TimeUtils.getTime(bean.get(position).getRelease_time());
        holder.releaseTime.setText(date);
        holder.dianjishu.setText(bean.get(position).getDianjishu() + "");
        utils.display(holder.img, bean.get(position).getImg());
        holder.zx_llt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.zx_llt,position);
            }
        });
      /*  holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewImageActivity.class);
                ArrayList<Bitmap> tempList = new ArrayList();
                tempList.add(holder.img.getDrawingCache());
                intent.putParcelableArrayListExtra("imgList", tempList);
                mContext.startActivity(intent);
            }
        });*/
    }

    public int getItemCount() {
        return bean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, subtitle, releaseTime, dianjishu;
        private LinearLayout zx_llt;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            releaseTime = (TextView) itemView.findViewById(R.id.releaseTime);
            dianjishu = (TextView) itemView.findViewById(R.id.dianjishu);
            img = (ImageView) itemView.findViewById(R.id.img);
            zx_llt = (LinearLayout) itemView.findViewById(R.id.zx_llt);
            img.setDrawingCacheEnabled(true);
        }
    }
}
