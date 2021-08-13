package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

public class District {

    @Override
    public String toString() {
        return "District{" +
                "districtId='" + districtId + '\'' +
                ", districtName='" + districtName + '\'' +
                ", districtType='" + districtType + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", provinceId='" + provinceId + '\'' +
                '}';
    }

    public District(String districtId, String districtName, String districtType, Object lat, Object lng, String provinceId) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.districtType = districtType;
        this.lat = lat;
        this.lng = lng;
        this.provinceId = provinceId;
    }

    @SerializedName("district_id")
    private String districtId;
    @SerializedName("district_name")
    private String districtName;
    @SerializedName("district_type")
    private String districtType;
    @SerializedName("lat")
    private Object lat;
    @SerializedName("lng")
    private Object lng;
    @SerializedName("province_id")
    private String provinceId;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
