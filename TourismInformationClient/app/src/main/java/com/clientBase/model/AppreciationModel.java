package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/1/14.
 */

public class AppreciationModel implements Serializable {


    /**
     * appreciationImg : zk_2.jpg
     * appreciationId : 2
     * appreciationTime : 2020-01-14 15:35
     * appreciationMessage : 日月光华，旦复旦兮。先民的随心而就，三千年后，馈赠今人奇肆美妙、瑰丽多变的艺术享受，令人击节赞叹。
     * appreciationTitle : 混沌初开乾坤朗朗
     */

    private String appreciationImg;
    private int appreciationId;
    private String appreciationTime;
    private String appreciationMessage;
    private String appreciationTitle;

    public String getAppreciationImg() {
        return appreciationImg;
    }

    public void setAppreciationImg(String appreciationImg) {
        this.appreciationImg = appreciationImg;
    }

    public int getAppreciationId() {
        return appreciationId;
    }

    public void setAppreciationId(int appreciationId) {
        this.appreciationId = appreciationId;
    }

    public String getAppreciationTime() {
        return appreciationTime;
    }

    public void setAppreciationTime(String appreciationTime) {
        this.appreciationTime = appreciationTime;
    }

    public String getAppreciationMessage() {
        return appreciationMessage;
    }

    public void setAppreciationMessage(String appreciationMessage) {
        this.appreciationMessage = appreciationMessage;
    }

    public String getAppreciationTitle() {
        return appreciationTitle;
    }

    public void setAppreciationTitle(String appreciationTitle) {
        this.appreciationTitle = appreciationTitle;
    }
}
