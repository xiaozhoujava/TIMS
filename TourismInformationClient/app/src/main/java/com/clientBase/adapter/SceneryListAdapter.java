package com.clientBase.adapter;

import java.util.List;

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

public class SceneryListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<SceneryModel> list_result;
	private int posIndex;
	private Context mContext;

	public SceneryListAdapter(Context context, List<SceneryModel> list_result) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.guide_list_layout, null);
			holder = new ViewHolder();
			holder.guide_city = (TextView) convertView.findViewById(R.id.guide_city);
			holder.guide_title = (TextView) convertView.findViewById(R.id.guide_title);
			holder.guide_image = (ImageView) convertView.findViewById(R.id.guide_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.guide_title.setText("票价："+list_result.get(position).getSceneryMoney()+"元");
			holder.guide_city.setText(list_result.get(position).getSceneryTitle());

			if(!TextUtils.isEmpty(list_result.get(position).getSceneryImage())){
				Picasso.with(mContext).load(Consts.URL_IMAGE+list_result.get(position).getSceneryImage()).placeholder(R.drawable.default_drawable_show_pictrue)
						.into(holder.guide_image);
			}


		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {

		private TextView guide_title,guide_city;
		private ImageView guide_image;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
