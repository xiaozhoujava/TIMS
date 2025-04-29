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
import com.clientBase.model.OrderBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<OrderBean> list_result;
    private int posIndex;
    private Context mContext;

    public OrderAdapter(Context context, List<OrderBean> list_result) {
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
            convertView = inflater.inflate(R.layout.item_order, null);
            holder = new ViewHolder();
            holder.mtvaddress = (TextView) convertView.findViewById(R.id.mtvaddress);
            holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoney);
            holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
            holder.mTvNo = (TextView) convertView.findViewById(R.id.mTvNo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvNo.setText(list_result.get(position).getOrderNo());

        holder.mTvMoney.setText("￥" + list_result.get(position).getOrderMessageMoney());
        holder.mtvTime.setText("时间：" + list_result.get(position).getOrderCreatime());
        holder.mtvaddress.setText("地址：" + list_result.get(position).getOrderAddress());
        return convertView;

    }

    private class ViewHolder {
        private TextView mtvaddress;
        private TextView mTvMoney;
        private TextView mtvTime;
        private TextView mTvNo;
    }

    public void setPos(int pos) {
        posIndex = pos;
    }

}
