package com.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.R;
import com.clientBase.model.LineModel;
import com.clientBase.model.TypeModel;

import java.util.List;

public class LikeAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<TypeModel> list_result;
	private int posIndex;
	private Context mContext;

	public LikeAdapter(Context context, List<TypeModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_message_like, null);
			holder = new ViewHolder();
			holder.newsShowTitle = (TextView) convertView.findViewById(R.id.newsShowTitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if(list_result.get(position).isChoice()){
			holder.newsShowTitle.setText(list_result.get(position).getTypeName()+"(已选择)");
		}else{
			holder.newsShowTitle.setText(list_result.get(position).getTypeName());
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView newsShowTitle;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
