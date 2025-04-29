package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2021/12/20.
 */

public class ReviewNoticeModel implements Serializable {


    /**
     * reviewUserId : 1
     * reviewMessageName : 52
     * reviewSendUserId : 52
     * reviewTime : 1
     * reviewContent : 1
     * reviewUserName : 1
     * reviewMessageId : 1
     * reviewId : 1
     */

    private int reviewUserId;
    private String reviewMessageName;
    private int reviewSendUserId;
    private String reviewTime;
    private String reviewContent;
    private String reviewUserName;
    private int reviewMessageId;
    private int reviewId;

    public int getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(int reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public String getReviewMessageName() {
        return reviewMessageName;
    }

    public void setReviewMessageName(String reviewMessageName) {
        this.reviewMessageName = reviewMessageName;
    }

    public int getReviewSendUserId() {
        return reviewSendUserId;
    }

    public void setReviewSendUserId(int reviewSendUserId) {
        this.reviewSendUserId = reviewSendUserId;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public int getReviewMessageId() {
        return reviewMessageId;
    }

    public void setReviewMessageId(int reviewMessageId) {
        this.reviewMessageId = reviewMessageId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}
