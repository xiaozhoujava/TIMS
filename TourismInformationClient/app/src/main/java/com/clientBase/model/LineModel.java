package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/12/16.
 */

public class LineModel implements Serializable {

    /**
     * lineDay : 一天
     * lineTime : 2019-12-16
     * lineCityName : 西安
     * lineMessage : 11111111111111111111
     * lineName : 11
     * lineCityId : 1
     * lineId : 1
     */

    private String lineDay;
    private String lineTime;
    private String lineCityName;
    private String lineMessage;
    private String lineName;
    private int lineCityId;
    private int lineId;

    public String getLineDay() {
        return lineDay;
    }

    public void setLineDay(String lineDay) {
        this.lineDay = lineDay;
    }

    public String getLineTime() {
        return lineTime;
    }

    public void setLineTime(String lineTime) {
        this.lineTime = lineTime;
    }

    public String getLineCityName() {
        return lineCityName;
    }

    public void setLineCityName(String lineCityName) {
        this.lineCityName = lineCityName;
    }

    public String getLineMessage() {
        return lineMessage;
    }

    public void setLineMessage(String lineMessage) {
        this.lineMessage = lineMessage;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getLineCityId() {
        return lineCityId;
    }

    public void setLineCityId(int lineCityId) {
        this.lineCityId = lineCityId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }
}
