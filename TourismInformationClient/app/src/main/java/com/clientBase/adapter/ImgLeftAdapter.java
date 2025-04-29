package com.clientBase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.client.R;
import com.clientBase.listener.ChoiceCourseListner;
import com.clientBase.model.CourseModel;

import java.util.List;

public class ImgLeftAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<CourseModel> list_result;
    private int posIndex;
    private Context mContext;
    private ChoiceCourseListner mChoiceCourseListner;
    public ImgLeftAdapter(Context context, List<CourseModel> list_result, ChoiceCourseListner choiceCourseListner) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.list_result = list_result;
        this.mChoiceCourseListner = choiceCourseListner;
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
            convertView = inflater.inflate(R.layout.item_img_left, null);
            holder = new ViewHolder();
            holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
            holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoney);
            holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
            holder.mivShop = (ImageView) convertView.findViewById(R.id.mivShop);
            holder.choiceCourse = (TextView) convertView.findViewById(R.id.choiceCourse);
            holder.mtvxueqi = (TextView) convertView.findViewById(R.id.mtvxueqi);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvTitle.setText(list_result.get(position).getCourseName());
        holder.mTvMoney.setText(list_result.get(position).getCourseType());
        holder.mtvTime.setText("时间：" + list_result.get(position).getCourseTime());

        if(list_result.get(position).isChoiceState()){
            holder.choiceCourse.setText("已选课");
        }else{
            holder.choiceCourse.setText("未选课");
        }




        holder.choiceCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChoiceCourseListner.setChoiceCourse(position,list_result.get(position));
            }
        });

        return convertView;

    }

    private class ViewHolder {
        private TextView mTvTitle;
        private TextView mTvMoney;
        private TextView mtvTime,choiceCourse,mtvxueqi;
        private ImageView mivShop;
    }

    public void setPos(int pos) {
        posIndex = pos;
    }

}
