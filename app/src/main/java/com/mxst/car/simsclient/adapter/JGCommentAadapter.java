package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.JGDetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class JGCommentAadapter extends RecyclerView.Adapter<JGCommentAadapter.ViewHolder> {
    private List<JGDetail.CommentsEntity> bean;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public JGCommentAadapter(Context context, List<JGDetail.CommentsEntity> bean) {
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

    public JGCommentAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_jg_comment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameTv.setText(bean.get(position).getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(bean.get(position).getComment_date());
        holder.dateTv.setText(sdf.format(date));
        holder.comTv.setText(bean.get(position).getContent());
        holder.rb.setRating(bean.get(position).getStar());
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
        private TextView nameTv, dateTv, comTv;
        private RatingBar rb;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.item_jg_name);
            dateTv = (TextView) itemView.findViewById(R.id.item_jg_date);
            comTv = (TextView) itemView.findViewById(R.id.item_jg_comment);
            rb = (RatingBar) itemView.findViewById(R.id.item_jg_rb);

        }
    }
}

