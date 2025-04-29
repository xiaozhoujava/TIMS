package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2021/12/20.
 */

public class PraiseModel implements Serializable {


    /**
     * praiseMessageName :
     * praiseUserName :
     * praiseSendUserId : 52
     * praiseId : 36
     * praiseMessageId : 24
     * praiseUserId : 52
     */

    private String praiseMessageName;
    private String praiseUserName;
    private int praiseSendUserId;
    private int praiseId;
    private int praiseMessageId;
    private int praiseUserId;

    public String getPraiseMessageName() {
        return praiseMessageName;
    }

    public void setPraiseMessageName(String praiseMessageName) {
        this.praiseMessageName = praiseMessageName;
    }

    public String getPraiseUserName() {
        return praiseUserName;
    }

    public void setPraiseUserName(String praiseUserName) {
        this.praiseUserName = praiseUserName;
    }

    public int getPraiseSendUserId() {
        return praiseSendUserId;
    }

    public void setPraiseSendUserId(int praiseSendUserId) {
        this.praiseSendUserId = praiseSendUserId;
    }

    public int getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(int praiseId) {
        this.praiseId = praiseId;
    }

    public int getPraiseMessageId() {
        return praiseMessageId;
    }

    public void setPraiseMessageId(int praiseMessageId) {
        this.praiseMessageId = praiseMessageId;
    }

    public int getPraiseUserId() {
        return praiseUserId;
    }

    public void setPraiseUserId(int praiseUserId) {
        this.praiseUserId = praiseUserId;
    }
}
