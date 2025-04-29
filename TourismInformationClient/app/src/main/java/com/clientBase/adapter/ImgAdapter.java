package com.clientBase.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.config.Consts;
import com.clientBase.model.ShareModel;
import com.clientBase.model.UserModel;
import com.clientBase.photo.ui.ShowPictureActivity;
import com.clientBase.view.GridviewForScrollView;
import com.clientBase.view.RoundedCornerImageView;

import java.util.List;

public class ImgAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ShareModel> list_result;
	private int posIndex;
	private Context mContext;
	 String imgMsg = "";
	public ImgAdapter(Context context, List<ShareModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_notice_rank, null);
			holder = new ViewHolder();
			holder.userTime = (TextView) convertView.findViewById(R.id.userTime);
			holder.userName = (TextView) convertView.findViewById(R.id.userName);
			holder.userMesage = (TextView) convertView.findViewById(R.id.userMesage);
			holder.mGridViewMessage = (GridviewForScrollView) convertView.findViewById(R.id.mGridViewMessage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.userName.setText(list_result.get(position).getShareUserName());
		holder.userTime.setText(list_result.get(position).getShareTime());
		holder.userMesage.setText(list_result.get(position).getShareMessage());

//		imgMsg="";
//		for(int i=0;i<list_result.get(position).getImgMsg().size();i++){
//			imgMsg=imgMsg+list_result.get(position).getImgMessage().get(i).getImgMsg()+",";
//		}
		TopicImgAdapter campusAdapter  = new TopicImgAdapter(mContext,list_result.get(position).getShareImg().split(","));
		holder.mGridViewMessage.setAdapter(campusAdapter);
		holder.mGridViewMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				Intent intent = new Intent(mContext, ShowPictureActivity.class);
				intent.putExtra("piction_path", Consts.URL_IMAGE +  list_result.get(position).getShareImg().split(",")[i]);
				mContext.startActivity(intent);
			}
		});
		return convertView;

	}

	private class ViewHolder {
		private TextView userName,userTime,userMesage;
		private GridviewForScrollView mGridViewMessage ;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
