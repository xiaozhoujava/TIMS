package com.clientBase.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.config.Consts;
import com.clientBase.model.SceneryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopicImgAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private String[] mData;
	private int posIndex;
	private Context mContext;

	public TopicImgAdapter(Context context, String[] data) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		mData = data;
	}

	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		return mData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.topic_img_layout, null);
			holder = new ViewHolder();
			holder.topic_image = (ImageView) convertView.findViewById(R.id.topic_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {


			if (!TextUtils.isEmpty(mData[position])) {
				Picasso.with(mContext).load(Consts.URL_IMAGE + mData[position])
						.placeholder(R.drawable.default_drawable_show_pictrue).into(holder.topic_image);
			}

		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {

		private ImageView topic_image;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
