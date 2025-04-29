package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/12/27.
 */

public class JewelryModel implements Serializable {


    /**
     * jewelryMoney : 150
     * jewelryImage : 1505283939521.jpg
     * jewelryMessage : 秦始皇兵马俑博物馆，又称兵马俑、秦兵马俑，是秦始皇陵的陪葬坑，和秦始皇帝陵博物馆为同一个景点，采用一票制。秦始皇兵马俑素有世界第八大奇迹之称，这里出土的一千多个士兵陶俑，形象各不相同，神态生动，是中国古代雕塑艺术史上的一颗明珠，被誉为“二十世纪考古史上的伟大发现之一”。
     * jewelryTitle : 秦始皇兵马俑博物馆
     * jewelryId : 2
     * jewelryTime : 2019-12-09
     */

    private String jewelryMoney;
    private String jewelryImage;
    private String jewelryMessage;
    private String jewelryTitle;
    private int jewelryId;
    private String jewelryTime;

    public String getJewelryMoney() {
        return jewelryMoney;
    }

    public void setJewelryMoney(String jewelryMoney) {
        this.jewelryMoney = jewelryMoney;
    }

    public String getJewelryImage() {
        return jewelryImage;
    }

    public void setJewelryImage(String jewelryImage) {
        this.jewelryImage = jewelryImage;
    }

    public String getJewelryMessage() {
        return jewelryMessage;
    }

    public void setJewelryMessage(String jewelryMessage) {
        this.jewelryMessage = jewelryMessage;
    }

    public String getJewelryTitle() {
        return jewelryTitle;
    }

    public void setJewelryTitle(String jewelryTitle) {
        this.jewelryTitle = jewelryTitle;
    }

    public int getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(int jewelryId) {
        this.jewelryId = jewelryId;
    }

    public String getJewelryTime() {
        return jewelryTime;
    }

    public void setJewelryTime(String jewelryTime) {
        this.jewelryTime = jewelryTime;
    }
}
