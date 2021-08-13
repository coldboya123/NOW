package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse{

    @SerializedName("results")
    private List<City> Citys;

    public List<City> getCityList() {
        return Citys;
    }

    public void setCityList(List<City> Citys) {
        this.Citys = Citys;
    }
}
