package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/12/27.
 */

public class OfforModel implements Serializable {


    /**
     * offerMoney : 899
     * offerType : 1
     * offerUserId : 37
     * offerTime : 2019-12-27 15:00
     * offerMessageId : 9
     * offerUserName : pony99
     * offerId : 1
     */

    private String offerMoney;
    private String offerType;
    private int offerUserId;
    private String offerTime;
    private int offerMessageId;
    private String offerUserName;
    private int offerId;
    private String offerMessageName;

    public String getOfferMessageName() {
        return offerMessageName;
    }

    public void setOfferMessageName(String offerMessageName) {
        this.offerMessageName = offerMessageName;
    }

    public String getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(String offerMoney) {
        this.offerMoney = offerMoney;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public int getOfferUserId() {
        return offerUserId;
    }

    public void setOfferUserId(int offerUserId) {
        this.offerUserId = offerUserId;
    }

    public String getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
    }

    public int getOfferMessageId() {
        return offerMessageId;
    }

    public void setOfferMessageId(int offerMessageId) {
        this.offerMessageId = offerMessageId;
    }

    public String getOfferUserName() {
        return offerUserName;
    }

    public void setOfferUserName(String offerUserName) {
        this.offerUserName = offerUserName;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }
}
