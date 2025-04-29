package com.clientBase.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2021/12/8.
 */

public class MainShopModel implements Serializable {

    private List<ShopModel>  allShop;
    private List<ShopModel>  recommendShop;

    public List<ShopModel> getAllShop() {
        return allShop;
    }

    public void setAllShop(List<ShopModel> allShop) {
        this.allShop = allShop;
    }

    public List<ShopModel> getRecommendShop() {
        return recommendShop;
    }

    public void setRecommendShop(List<ShopModel> recommendShop) {
        this.recommendShop = recommendShop;
    }
}
