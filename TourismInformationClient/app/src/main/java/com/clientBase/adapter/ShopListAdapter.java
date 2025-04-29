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

public class ShopListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ShopModel> list_result;
	private int posIndex;
	private Context mContext;
	public ShopListAdapter(Context context, List<ShopModel> list_result) {
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
			convertView = inflater.inflate(R.layout.look_item, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoney);
			holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
			holder.mivShop = (ImageView) convertView.findViewById(R.id.mivShop);

			holder.mtv4 = (TextView) convertView.findViewById(R.id.mtv4);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
			holder.mTvTitle.setText(list_result.get(position).getShopName());
			holder.mTvMoney.setText("￥" + list_result.get(position).getShopMoney());
			holder.mtvTime.setText(list_result.get(position).getShopTypeName()+","+list_result.get(position).getShopFZName());

			if (!TextUtils.isEmpty(list_result.get(position).getShopImg())) {
				Picasso.with(mContext).load(Consts.URL_IMAGE + list_result.get(position).getShopImg())
						.placeholder(R.drawable.default_drawable_show_pictrue).into(holder.mivShop);
			}



		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle,mtv4;
		private TextView mTvMoney;
		private TextView mtvTime;
		private ImageView mivShop;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
