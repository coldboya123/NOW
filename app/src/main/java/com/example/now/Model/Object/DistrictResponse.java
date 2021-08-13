package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictResponse {

    @SerializedName("results")
    private List<District> districtList;

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }
}
