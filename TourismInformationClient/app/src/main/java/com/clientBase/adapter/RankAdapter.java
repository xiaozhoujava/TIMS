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
import com.clientBase.model.ShopModel;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * 消息适配器
 */
public class RankAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ShopModel> list_result;
	private int posIndex;
	private Context mContext;

	public RankAdapter(Context context, List<ShopModel> list_result) {
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
			convertView = inflater.inflate(R.layout.guide_list_layout, null);
			holder = new ViewHolder();
			holder.guide_title = (TextView) convertView.findViewById(R.id.guide_title);
			holder.guide_city = (TextView) convertView.findViewById(R.id.guide_city);
			holder.guide_image = (ImageView) convertView.findViewById(R.id.guide_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.guide_title.setText(list_result.get(position).getShopName());
		holder.guide_city.setText("￥"+list_result.get(position).getShopMoney());
		if (!TextUtils.isEmpty(list_result.get(position).getShopImg())) {
			Picasso.with(mContext).load(Consts.URL_IMAGE + list_result.get(position).getShopImg())
					.placeholder(R.drawable.default_drawable_show_pictrue).into(holder.guide_image);
		}
		return convertView;

	}

	private class ViewHolder {
		private TextView guide_title,guide_city;
		ImageView guide_image;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
