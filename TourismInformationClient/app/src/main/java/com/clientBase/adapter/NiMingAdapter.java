package com.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.listener.TopicListner;
import com.clientBase.model.TopicModel;
import com.clientBase.view.CircleImageView;
import com.clientBase.view.GridviewForScrollView;

import java.util.List;


public class NiMingAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<TopicModel> list_result;
    private int posIndex;
    private Context mContext;
    TopicListner mtopicListner;

    public NiMingAdapter(Context context, List<TopicModel> list_result, TopicListner topicListner) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.list_result = list_result;
        this.mtopicListner = topicListner;
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


        holder.contentInfor.setText(list_result.get(position).getTopicInfor());


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
