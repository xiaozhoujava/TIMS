package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/8/27.
 */

public class TopicModel implements Serializable {


    /**
     * topicUserName : 小样
     * topicInfor : 曾经输掉的东西，只要你想，就可以一点一点再赢回来。
     * topicImg : nvsheng3.jpg
     * topicUserId : 51
     * topicFlag : 1
     * topicTypeInfor : 分享,画画,双子座
     * topicId : 22
     * topicTime : 2020-10-27 11:35
     */

    private String topicUserName;
    private String topicInfor;
    private String topicImg;
    private String topicUserId;
    private String topicFlag;
    private String topicTypeInfor;
    private int topicId;
    private String topicTime;

    private int collectNumber;
    private int praiseNumber;


    private boolean praiseState;
    private boolean collectState;


    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    public int getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(int praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public boolean isPraiseState() {
        return praiseState;
    }

    public void setPraiseState(boolean praiseState) {
        this.praiseState = praiseState;
    }

    public boolean isCollectState() {
        return collectState;
    }

    public void setCollectState(boolean collectState) {
        this.collectState = collectState;
    }

    public String getTopicUserName() {
        return topicUserName;
    }

    public void setTopicUserName(String topicUserName) {
        this.topicUserName = topicUserName;
    }

    public String getTopicInfor() {
        return topicInfor;
    }

    public void setTopicInfor(String topicInfor) {
        this.topicInfor = topicInfor;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicUserId() {
        return topicUserId;
    }

    public void setTopicUserId(String topicUserId) {
        this.topicUserId = topicUserId;
    }

    public String getTopicFlag() {
        return topicFlag;
    }

    public void setTopicFlag(String topicFlag) {
        this.topicFlag = topicFlag;
    }

    public String getTopicTypeInfor() {
        return topicTypeInfor;
    }

    public void setTopicTypeInfor(String topicTypeInfor) {
        this.topicTypeInfor = topicTypeInfor;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(String topicTime) {
        this.topicTime = topicTime;
    }
}
