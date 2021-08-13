package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WardResponse {

    @SerializedName("results")
    private List<Ward> wardList;

    public List<Ward> getWardList() {
        return wardList;
    }

    @Override
    public String toString() {
        return "WardResponse{" +
                "wardList=" + wardList +
                '}';
    }

    public WardResponse(List<Ward> wardList) {
        this.wardList = wardList;
    }

    public void setWardList(List<Ward> wardList) {
        this.wardList = wardList;
    }
}
