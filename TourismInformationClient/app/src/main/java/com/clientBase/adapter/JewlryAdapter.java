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
import com.clientBase.model.FoodModel;
import com.clientBase.model.JewelryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JewlryAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<JewelryModel> list_result;
	private int posIndex;
	private Context mContext;

	public JewlryAdapter(Context context, List<JewelryModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_img_left, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoney);
			holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
			holder.mivShop = (ImageView) convertView.findViewById(R.id.mivShop);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

			holder.mTvTitle.setText(list_result.get(position).getJewelryTitle());
			holder.mTvMoney.setText("价格："+list_result.get(position).getJewelryMoney()+"元");
			holder.mtvTime.setText("时间：" + list_result.get(position).getJewelryTime());
			if (!TextUtils.isEmpty(list_result.get(position).getJewelryImage())) {
				Picasso.with(mContext).load(Consts.URL_IMAGE + list_result.get(position).getJewelryImage())
						.placeholder(R.drawable.default_drawable_show_pictrue).into(holder.mivShop);
			}


		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle;
		private TextView mTvMoney;
		private TextView mtvTime;
		private ImageView mivShop;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
