package com.example.gridviewdemo1;

import java.util.List;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<ItemBean>{
	private Context mContext;
	private List<ItemBean> mList;
	private LayoutInflater mInflater;
	public DataAdapter(Context context, List<ItemBean> list) {
		super(context, 0, list);
		this.mContext = context;
		this.mList = list;
		this.mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	@Override
	public int getCount() {
		return mList.size();
	}
	@Override
	public ItemBean getItem(int position) {
		return mList.get(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder = null;
		ItemBean mBean = mList.get(position);
		// 初始化控件
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item, parent, false);
			mHolder = new ViewHolder();
			mHolder.mid = (TextView) convertView.findViewById(R.id.TextView1);
			mHolder.mred = (TextView) convertView
					.findViewById(R.id.TextView2);
			mHolder.mgreen = (TextView) convertView
					.findViewById(R.id.TextView3);
			mHolder.myellow = (TextView) convertView
					.findViewById(R.id.TextView4);
			
			
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		// 给控件设定数值
		if (mBean != null) {
			mHolder.mid.setText(String.valueOf(mBean.getId()));
			mHolder.mred.setText(String.valueOf(mBean.getRed()));
			mHolder.myellow.setText(String.valueOf(mBean.getYellow()));
			mHolder.mgreen.setText(String.valueOf(mBean.getGreen()));
			mHolder.mid.setBackgroundColor(Color.parseColor("white"));
			mHolder.mred.setBackgroundColor(Color.parseColor("white"));
			mHolder.myellow.setBackgroundColor(Color.parseColor("white"));
			mHolder.mgreen.setBackgroundColor(Color.parseColor("white"));
			
		}
		return convertView;
	}
	private class ViewHolder {
		TextView mid;
		TextView mred;
		TextView mgreen;
		TextView myellow;
		
	}

}
