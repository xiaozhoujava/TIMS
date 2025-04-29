package com.clientBase.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.model.NewsModel;


public class NewsListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<NewsModel> list_result;
	private int posIndex;
	private Context mContext;

	public NewsListAdapter(Context context, List<NewsModel> list_result) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
	}

	@Override
	public int getCount() {
		return list_result.size();
	}

	@Override
	public Object getItem(int position) {
		return list_result.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_news_msg, null);
			holder = new ViewHolder();
			holder.itemSize = (TextView) convertView.findViewById(R.id.itemSize);
			holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
			holder.itemTime = (TextView) convertView.findViewById(R.id.itemTime);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			
			holder.itemTitle.setText(list_result.get(position).getNewsTitle());
			holder.itemTime.setVisibility(View.GONE);
			holder.itemSize.setText("发布日期："+list_result.get(position).getNewsTime());
		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView itemSize,itemTime;
		private TextView itemTitle;
		private ImageView itemImage;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}