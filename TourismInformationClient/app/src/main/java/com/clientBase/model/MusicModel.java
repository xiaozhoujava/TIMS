package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/1/14.
 */

public class MusicModel implements Serializable {



    /**
     * musicTypeId : 31
     * musicImage : 1560239276893.jpg
     * musicTitle : 篆刻历史介绍
     * musicId : 79
     * musicCreatTime : 2020-01-14 15:02:43
     * musicMessage : 早在殷商时代，人们就用刀在龟甲上刻“字”（即现代称为甲骨文）。这些文字刀锋挺锐，笔意劲秀，具有较高的“刻字”水平。在春秋战国至秦以前，篆刻印章称为“玺”或鉨，玺为在玉上刻制的，鉨是金属上刻制的。秦始皇统一六国后，规定“玺”为天子所专用，大臣以下和民间私人用印统称“印”，从此鉨就废而不用了。这就形成了帝王用印称“玺”或“宝”，官印称“印”，将军用印称“章”，私人用印称“印信”。
     * musicTypeName : 第一章
     * musicFile : music3.mp3
     */

    private String musicTypeId;
    private String musicImage;
    private String musicTitle;
    private int musicId;
    private String musicCreatTime;
    private String musicMessage;
    private String musicTypeName;
    private String musicFile;

    public String getMusicTypeId() {
        return musicTypeId;
    }

    public void setMusicTypeId(String musicTypeId) {
        this.musicTypeId = musicTypeId;
    }

    public String getMusicImage() {
        return musicImage;
    }

    public void setMusicImage(String musicImage) {
        this.musicImage = musicImage;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicCreatTime() {
        return musicCreatTime;
    }

    public void setMusicCreatTime(String musicCreatTime) {
        this.musicCreatTime = musicCreatTime;
    }

    public String getMusicMessage() {
        return musicMessage;
    }

    public void setMusicMessage(String musicMessage) {
        this.musicMessage = musicMessage;
    }

    public String getMusicTypeName() {
        return musicTypeName;
    }

    public void setMusicTypeName(String musicTypeName) {
        this.musicTypeName = musicTypeName;
    }

    public String getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }
}
