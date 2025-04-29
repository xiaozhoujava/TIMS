package com.clientBase.model;

import java.io.Serializable;

public class NewsModel implements Serializable {
	
	private String newsId;
	private String newsTitle;
	private String newsMessage;
	private String newsTime;
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsMessage() {
		return newsMessage;
	}
	public void setNewsMessage(String newsMessage) {
		this.newsMessage = newsMessage;
	}
	public String getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}
	

}
