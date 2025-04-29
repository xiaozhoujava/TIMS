package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2019/12/9.
 */

public class FoodModel implements Serializable {



    /**
     * foodImg : 17.png
     * foodTime : 2019-12-09
     * foodId : 3
     * foodAddress : 西安市雁塔区
     * foodMessage : 大唐不夜城的中轴景观大道是一条1500米横贯南北的中央雕塑景观步行街，其上分布着盛世帝王、历史人物、英雄故事、经典艺术作品等九组主题群雕，立体展现大唐帝国在宗教、文学、艺术、科技等领域的至尊地位并彰显大国气象。
     * foodName : 家常豆腐
     * foodShopName : 豆腐商家
     */

    private String foodImg;
    private String foodTime;
    private int foodId;
    private String foodAddress;
    private String foodMessage;
    private String foodName;
    private String foodShopName;

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodAddress() {
        return foodAddress;
    }

    public void setFoodAddress(String foodAddress) {
        this.foodAddress = foodAddress;
    }

    public String getFoodMessage() {
        return foodMessage;
    }

    public void setFoodMessage(String foodMessage) {
        this.foodMessage = foodMessage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodShopName() {
        return foodShopName;
    }

    public void setFoodShopName(String foodShopName) {
        this.foodShopName = foodShopName;
    }
}
