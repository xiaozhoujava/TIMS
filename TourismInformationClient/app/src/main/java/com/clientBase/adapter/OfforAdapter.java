package com.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.R;
import com.clientBase.model.LineModel;
import com.clientBase.model.OfforModel;

import java.util.List;

public class OfforAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<OfforModel> list_result;
	private int posIndex;
	private Context mContext;

	public OfforAdapter(Context context, List<OfforModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_message_course, null);
			holder = new ViewHolder();
			holder.newsShowTime = (TextView) convertView.findViewById(R.id.newsShowTime);
			holder.newsShowTitle = (TextView) convertView.findViewById(R.id.newsShowTitle);
			holder.newsShowMessage = (TextView) convertView.findViewById(R.id.newsShowMessage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.newsShowTitle.setText(list_result.get(position).getOfferMessageName());
		holder.newsShowMessage.setText("出示金额：" + list_result.get(position).getOfferMoney()+"元");
		return convertView;

	}

	private class ViewHolder {
		private TextView newsShowTitle;
		private TextView newsShowTime;
		private TextView newsShowMessage;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
