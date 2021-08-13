package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

public class City {

    @Override
    public String toString() {
        return "City{" +
                "provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceType='" + provinceType + '\'' +
                '}';
    }

    public City(String provinceId, String provinceName, String provinceType) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.provinceType = provinceType;
    }

    @SerializedName("province_id")
    private String provinceId;
    @SerializedName("province_name")
    private String provinceName;
    @SerializedName("province_type")
    private String provinceType;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceType() {
        return provinceType;
    }

    public void setProvinceType(String provinceType) {
        this.provinceType = provinceType;
    }
}
