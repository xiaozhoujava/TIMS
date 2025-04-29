package com.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.R;
import com.clientBase.model.PraiseModel;
import com.clientBase.model.ReviewNoticeModel;

import java.util.List;


public class ReviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ReviewNoticeModel> list_result;
    private int posIndex;
    private Context mContext;

    public ReviewAdapter(Context context, List<ReviewNoticeModel> list_result) {
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
            convertView = inflater.inflate(R.layout.item_niming, null);
            holder = new ViewHolder();
            holder.titleInfor = (TextView) convertView.findViewById(R.id.titleInfor);
            holder.contentInfor = (TextView) convertView.findViewById(R.id.contentInfor);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleInfor.setText(list_result.get(position).getReviewUserName()+"评论内容是："+list_result.get(position).getReviewContent());
        holder.contentInfor.setText("攻略是："+list_result.get(position).getReviewMessageName());

        return convertView;

    }

    private class ViewHolder {
        private TextView titleInfor;
        private TextView contentInfor;

    }

    public void setPos(int pos) {
        posIndex = pos;
    }

}
