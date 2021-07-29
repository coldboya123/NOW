package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupFood {

    @SerializedName("subCate_id")
    private String subcateId;
    @SerializedName("shop_id")
    private String shopId;
    @SerializedName("subCate_name")
    private String subcateName;
    @SerializedName("list_food")
    private List<Food> listFood;

    @Override
    public String toString() {
        return "GroupFood{" +
                "subcateId='" + subcateId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", subcateName='" + subcateName + '\'' +
                ", listFood=" + listFood +
                '}';
    }

    public GroupFood(String subcateId, String shopId, String subcateName, List<Food> listFood) {
        this.subcateId = subcateId;
        this.shopId = shopId;
        this.subcateName = subcateName;
        this.listFood = listFood;
    }

    public String getSubcateId() {
        return subcateId;
    }

    public void setSubcateId(String subcateId) {
        this.subcateId = subcateId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSubcateName() {
        return subcateName;
    }

    public void setSubcateName(String subcateName) {
        this.subcateName = subcateName;
    }

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }
}
