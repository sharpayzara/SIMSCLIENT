package com.mxst.car.simsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxst.car.simsclient.R;
import com.mxst.car.simsclient.entity.BusinessList;

import java.util.List;


public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.BusinessHolder>{
	Context mContext;
	List<BusinessList.BusinessEntity> list;
	private boolean isUsed = true;
	private OnItemClickListener onItemClickListener;
	public BusinessListAdapter(Context context, List<BusinessList.BusinessEntity> list, boolean isUsed) {
		mContext = context;
		this.list = list;
		this.isUsed = isUsed;
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
	@Override
	public BusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		BusinessHolder holder = new BusinessHolder(LayoutInflater.from(mContext).inflate(R.layout.item_use_score, parent,
				false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final BusinessHolder holder, final int position) {
		holder.wxlx.setText(list.get(position).getWxlx());
		holder.noteDate.setText(list.get(position).getNoteDate());
		holder.fixName.setText(list.get(position).getFixName());
		holder.yjcurr.setText(list.get(position).getYjcurr()+"");
		holder.use_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(onItemClickListener != null) {
					onItemClickListener.onItemClick(holder.use_btn, position);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	class BusinessHolder extends RecyclerView.ViewHolder{
		TextView wxlx,noteDate,fixName,yjcurr;
		ImageView use_btn;
		public BusinessHolder(View view){
			super(view);
			wxlx = (TextView) view.findViewById(R.id.wxlx);
			noteDate = (TextView) view.findViewById(R.id.noteDate);
			fixName = (TextView) view.findViewById(R.id.fixName);
			yjcurr = (TextView) view.findViewById(R.id.yjcurr);
			use_btn = (ImageView) view.findViewById(R.id.use_btn);
		}
	}
	public interface OnItemClickListener{
		void onItemClick(View view, int position);
	}
}
