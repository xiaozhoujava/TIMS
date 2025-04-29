package com.clientBase.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.clientUI.TopicReviewActivity;

import java.util.List;

import io.rong.imkit.RongIM;


public class TopicAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<TopicModel> list_result;
    private int posIndex;
    private Context mContext;
    TopicListner mtopicListner;

    public TopicAdapter(Context context, List<TopicModel> list_result, TopicListner topicListner) {
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
            convertView = inflater.inflate(R.layout.item_topic, null);
            holder = new ViewHolder();
            holder.guide_message = (TextView) convertView.findViewById(R.id.guide_message);
            holder.guide_title = (TextView) convertView.findViewById(R.id.guide_title);
            holder.type_message = (TextView) convertView.findViewById(R.id.type_message);
            holder.lostTime = (TextView) convertView.findViewById(R.id.lostTime);

            holder.musicImage = (CircleImageView) convertView.findViewById(R.id.musicImage);
            holder.mgvTopicImg = (GridviewForScrollView) convertView.findViewById(R.id.mgvTopicImg);

            holder.impraise = (ImageView) convertView.findViewById(R.id.impraise);
            holder.imcollect = (ImageView) convertView.findViewById(R.id.imcollect);
            holder.imreview = (ImageView) convertView.findViewById(R.id.imreview);

            holder.praiseNumber = (TextView) convertView.findViewById(R.id.praiseNumber);
            holder.collectNumber = (TextView) convertView.findViewById(R.id.collectNumber);
            holder.reviewNumber = (TextView) convertView.findViewById(R.id.reviewNumber);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        String inforTopic =list_result.get(position).getTopicInfor();
        holder.guide_message.setText(inforTopic);
        holder.type_message.setText("#" + list_result.get(position).getTopicTypeInfor());
        holder.guide_title.setText(list_result.get(position).getTopicUserName());

        holder.praiseNumber.setText(list_result.get(position).getPraiseNumber()+"");
        holder.collectNumber.setText(list_result.get(position).getCollectNumber()+"");


        holder.lostTime.setText(list_result.get(position).getTopicTime());

        if (list_result.get(position).getTopicImg().length() > 2) {
            holder.mgvTopicImg.setVisibility(View.VISIBLE);
            TopicImgAdapter topicImgAdapter = new TopicImgAdapter(mContext, list_result.get(position).getTopicImg().split(","));
            holder.mgvTopicImg.setAdapter(topicImgAdapter);
        } else {
            holder.mgvTopicImg.setVisibility(View.GONE);
        }


        holder.musicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIM.getInstance().startPrivateChat(mContext, list_result.get(position).getTopicUserId()+"", list_result.get(position).getTopicUserName());
            }
        });


//        if (!TextUtils.isEmpty(list_result.get(position).getTopicImg())) {
//            Picasso.with(mContext).load(Consts.URL_IMAGE + list_result.get(position).getTopicImg()).placeholder(R.drawable.default_drawable_show_pictrue).into(holder.musicImage);
//        }


//
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TopicReviewActivity.class);
                intent.putExtra("msg", list_result.get(position));
                mContext.startActivity(intent);
            }
        });

        holder.impraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtopicListner.setPraise(position, list_result.get(position));
            }
        });

        holder.imcollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtopicListner.setCollect(position, list_result.get(position));
            }
        });


        return convertView;

    }

    private class ViewHolder {
        private ImageView impraise, imcollect, imreview;
        private TextView guide_message;
        private TextView guide_title;
        private TextView type_message;
        private TextView lostTime;
        private TextView praiseNumber, collectNumber,reviewNumber;
        private CircleImageView musicImage;
        private GridviewForScrollView mgvTopicImg;

    }

    public void setPos(int pos) {
        posIndex = pos;
    }

}
