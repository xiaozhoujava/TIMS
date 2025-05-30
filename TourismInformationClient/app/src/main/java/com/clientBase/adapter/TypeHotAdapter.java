package com.clientBase.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.R;

public class TypeHotAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private String[] list_result;
	private int posIndex;
	private Context mContext;

	public TypeHotAdapter(Context context,String[] list_result) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
	}

	@Override
	public int getCount() {
		return list_result.length;
	}

	@Override
	public Object getItem(int position) {
		return list_result[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_typemessage, null);
			holder = new ViewHolder();
			holder.musicTitle = (TextView) convertView.findViewById(R.id.musicTitle);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.musicTitle.setText(list_result[position]);
			
//			if (!TextUtils.isEmpty(list_result.get(position).getBookImage())) {
//				Picasso.with(mContext).load(Consts.URL_IMAGE + list_result.get(position).getBookImage())
//						.placeholder(R.drawable.icon_xiaomi).into(holder.musicImage);
//			}


		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView musicTitle;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
