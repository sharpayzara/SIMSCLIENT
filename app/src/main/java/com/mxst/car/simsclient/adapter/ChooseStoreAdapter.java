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
import com.mxst.car.simsclient.entity.Stores;

import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class ChooseStoreAdapter extends RecyclerView.Adapter<ChooseStoreAdapter.ViewHolder> {
    private List<Stores.StoresEntity> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ChooseStoreAdapter(Context context, List<Stores.StoresEntity> bean) {
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

    public ChooseStoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_store_choose, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameTv.setText(bean.get(position).getName());
        holder.phoneTv.setText(bean.get(position).getPhone());
        holder.addressTv.setText(bean.get(position).getAddress());
        BitmapUtils bitmapUtils = new BitmapUtils(mContext);
        bitmapUtils.display(holder.img, bean.get(position).getStoreImg());

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
        private TextView nameTv, phoneTv, addressTv;
        private ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.item_store_name);
            phoneTv = (TextView) itemView.findViewById(R.id.item_store_phone);
            addressTv = (TextView) itemView.findViewById(R.id.item_store_adress);
            img = (ImageView) itemView.findViewById(R.id.item_store_img);

        }
    }
}

