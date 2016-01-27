package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.BrandResource;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/6.
 * version:  V1.0
 * Description:
 */
public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ViewHolder> {
    private List<BrandResource> bean;
    private LayoutInflater layoutInflater;

    public ResourceAdapter(Context context, List<BrandResource> bean) {
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

    public ResourceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_resourcefind, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ResourceAdapter.ViewHolder holder, final int position) {
        holder.itemResBrand.setText(bean.get(position).getBrand() + bean.get(position).getXinghao());
        holder.itemResCartype.setText(bean.get(position).getCarType());
        float d = Float.valueOf(bean.get(position).getTotalPrice()) / 10000;
        String money = String.format("%.2f", d);
        holder.itemResTotalprice.setText(money + "万元");
        holder.itemResKuaxing.setText(bean.get(position).getNianKuan() + bean.get(position).getKuanXing());
        holder.itemResGuige.setText(bean.get(position).getGuiGe());
        holder.item_res_incolor.setText(bean.get(position).getInColor());
        holder.itemResOutcolor.setText(bean.get(position).getOutColor() );
        if (bean.get(position).getGuidePrice() == 0) {
            holder.itemResGuideprice.setText("指导价:" + "暂无");
        } else {
            float s = Float.valueOf(bean.get(position).getGuidePrice()) / 10000;
            String m = String.format("%.2f", s);
            holder.itemResGuideprice.setText("指导价:" + m + "万元");
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
        private TextView itemResBrand, itemResCartype, itemResTotalprice,
                item_res_incolor,itemResKuaxing, itemResGuige, itemResOutcolor, itemResGuideprice;

        public ViewHolder(View itemView) {
            super(itemView);
            item_res_incolor = (TextView) itemView.findViewById(R.id.item_res_incolor);
            itemResBrand = (TextView) itemView.findViewById(R.id.item_res_brand);
            itemResCartype = (TextView) itemView.findViewById(R.id.item_res_cartype);
            itemResTotalprice = (TextView) itemView.findViewById(R.id.item_res_totalprice);
            itemResKuaxing = (TextView) itemView.findViewById(R.id.item_res_kuaxing);
            itemResOutcolor = (TextView) itemView.findViewById(R.id.item_res_outcolor);
            itemResGuideprice = (TextView) itemView.findViewById(R.id.item_res_guideprice);
            itemResGuige = (TextView) itemView.findViewById(R.id.item_res_guige);

        }
    }
}
