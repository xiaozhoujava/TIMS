package com.clientBase.model;

import com.clientBase.db.MemberUserUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/12/27.
 */

public class ReplyModel implements Serializable {


    private String replyMessgaeId;
    private String replyMessage;
    private String replyUserName;
    private String replyUserId;
    private String replyCreatime;

    public String getReplyCreatime() {
        return replyCreatime;
    }

    public void setReplyCreatime(String replyCreatime) {
        this.replyCreatime = replyCreatime;
    }

    public String getReplyMessgaeId() {
        return replyMessgaeId;
    }

    public void setReplyMessgaeId(String replyMessgaeId) {
        this.replyMessgaeId = replyMessgaeId;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }
}
