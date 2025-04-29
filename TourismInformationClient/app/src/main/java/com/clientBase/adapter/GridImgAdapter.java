package com.clientBase.adapter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.client.R;
import com.clientBase.config.Consts;
import com.clientBase.model.ViewHolder;
import com.squareup.picasso.Picasso;


public class GridImgAdapter extends BaseAdapter {

	private Context mContext;
	private String[] mData;
	// private SdcardImageLoader mSdcardImageLoader;
	Handler mHandler = new Handler();


	public GridImgAdapter(Context context, String[] data) {
		mContext = context;
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
		return position + 1;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);

			View lin_camera = ViewHolder.get(convertView, R.id.lin_camera);
			View imageItem = ViewHolder.get(convertView, R.id.img_item);
			ViewGroup.LayoutParams layoutParams = lin_camera.getLayoutParams();

			layoutParams.width = getImageWidth();
			layoutParams.height = getImageWidth();
			lin_camera.setLayoutParams(layoutParams);
			imageItem.setLayoutParams(layoutParams);
		}

		View lin_camera = ViewHolder.get(convertView, R.id.lin_camera);

		final ImageView imageSelector = ViewHolder.get(convertView, R.id.img_selector);

		final ImageView imageItem = ViewHolder.get(convertView, R.id.img_item);

		// 初始化
		final Animation alphaAnimation = new AlphaAnimation(1f, 0.5f);
		// 设置动画时间

		alphaAnimation.setDuration(500);
		alphaAnimation.setFillAfter(true);

		final Animation alphaAnimation2 = new AlphaAnimation(0.5f, 1f);
		// 设置动画时间

		alphaAnimation2.setDuration(500);
		alphaAnimation2.setFillAfter(true);
//		if (position == 0) {
//			lin_camera.setVisibility(View.VISIBLE);
//			imageSelector.setVisibility(View.GONE);
//			imageItem.setVisibility(View.GONE);
//		} else {
//			lin_camera.setVisibility(View.GONE);
//			imageSelector.setVisibility(View.VISIBLE);
//			imageItem.setVisibility(View.VISIBLE);
//
//			// mSdcardImageLoader.downLoad(mData.get(position -
//			// 1).get("path").toString(), imageItem, R.drawable.tmp);
////			ImageLoader.getInstance().displayImage("file://" + (String) mData.get(position - 1).get("path"), imageItem,
////					ImageLoaderOptions.getCommonOption(R.drawable.tmp, R.drawable.tmp, R.drawable.tmp)
////
////			);
//
//		}

			lin_camera.setVisibility(View.GONE);
			imageSelector.setVisibility(View.GONE);
			imageItem.setVisibility(View.VISIBLE);
		if (!TextUtils.isEmpty(mData[position])) {
			Picasso.with(mContext).load(Consts.URL_IMAGE + mData[position])
					.placeholder(R.drawable.default_drawable_show_pictrue).into(imageItem);
		}
		return convertView;
	}

	private int getImageWidth() {
		DisplayMetrics metric = mContext.getResources().getDisplayMetrics();
		final float scale = mContext.getResources().getDisplayMetrics().density;
		int dev = (int) (8 * scale + 0.5f);
		return (metric.widthPixels - dev) / 3; // 屏幕宽度（像素）
	}

}
