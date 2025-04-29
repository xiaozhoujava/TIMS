package com.clientBase.listener;


import com.clientBase.model.TopicModel;

public interface TopicListner {
	void setPraise(int pos, TopicModel topicModel);
	void setCollect(int pos, TopicModel topicModel);
}
