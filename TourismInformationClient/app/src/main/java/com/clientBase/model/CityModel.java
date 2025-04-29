package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2019/12/9.
 */

public class CityModel implements Serializable {

    private String cityId;
    private String cityName;
    private String cityCode;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
