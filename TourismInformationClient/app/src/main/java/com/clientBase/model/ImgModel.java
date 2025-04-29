package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/1/21.
 */

public class ImgModel implements Serializable {

    private String  imgMsg;
    private String  imgId;
    private String  imgUserId;
    private String  imgUserName;
    private String  imgTime;

    public String getImgMsg() {
        return imgMsg;
    }

    public void setImgMsg(String imgMsg) {
        this.imgMsg = imgMsg;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getImgUserId() {
        return imgUserId;
    }

    public void setImgUserId(String imgUserId) {
        this.imgUserId = imgUserId;
    }

    public String getImgUserName() {
        return imgUserName;
    }

    public void setImgUserName(String imgUserName) {
        this.imgUserName = imgUserName;
    }

    public String getImgTime() {
        return imgTime;
    }

    public void setImgTime(String imgTime) {
        this.imgTime = imgTime;
    }
}
