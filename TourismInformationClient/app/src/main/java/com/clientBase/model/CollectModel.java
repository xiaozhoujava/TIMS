package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2021/12/20.
 */

public class CollectModel implements Serializable {


    /**
     * collectUserId : 2
     * collectMessageName : 2
     * collectSendUserId : 52
     * collectUserName : 2
     * collectMessageId : 2
     * collectId : 11
     */

    private int collectUserId;
    private String collectMessageName;
    private int collectSendUserId;
    private String collectUserName;
    private int collectMessageId;
    private int collectId;

    public int getCollectUserId() {
        return collectUserId;
    }

    public void setCollectUserId(int collectUserId) {
        this.collectUserId = collectUserId;
    }

    public String getCollectMessageName() {
        return collectMessageName;
    }

    public void setCollectMessageName(String collectMessageName) {
        this.collectMessageName = collectMessageName;
    }

    public int getCollectSendUserId() {
        return collectSendUserId;
    }

    public void setCollectSendUserId(int collectSendUserId) {
        this.collectSendUserId = collectSendUserId;
    }

    public String getCollectUserName() {
        return collectUserName;
    }

    public void setCollectUserName(String collectUserName) {
        this.collectUserName = collectUserName;
    }

    public int getCollectMessageId() {
        return collectMessageId;
    }

    public void setCollectMessageId(int collectMessageId) {
        this.collectMessageId = collectMessageId;
    }

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }
}
