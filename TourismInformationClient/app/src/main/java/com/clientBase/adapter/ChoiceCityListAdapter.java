package com.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.R;
import com.clientBase.model.CityModel;

import java.util.List;

public class ChoiceCityListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<CityModel> list_result;
    private int posIndex;
    private Context mContext;

    public ChoiceCityListAdapter(Context context, List<CityModel> list_result) {
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
            convertView = inflater.inflate(R.layout.item_city_sg, null);
            holder = new ViewHolder();
            holder.cityTitle = (TextView) convertView.findViewById(R.id.cityTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.cityTitle.setText(list_result.get(position).getCityName());
        return convertView;

    }

    private class ViewHolder {
        private TextView cityTitle;
    }

    public void setPos(int pos) {
        posIndex = pos;
    }

}
