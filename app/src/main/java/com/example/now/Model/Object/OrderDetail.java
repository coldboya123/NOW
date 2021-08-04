package com.example.now.Model.Object;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetail {

    @SerializedName("shop_id")
    private String shopId;
    @SerializedName("shop_name")
    private String shopName;
    @SerializedName("price")
    private String price;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;
    @SerializedName("list_food")
    private List<FoodOrder> foodOrder;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @SuppressLint("DefaultLocale")
    public String getPriceFormated() {
        return String.format("%1$,.0f", Float.parseFloat(price)) + " đ";
    }

    @SuppressLint("DefaultLocale")
    public String getPrice2Formated(){
        return String.format("%1$,.0f", Float.parseFloat(price)-30000) + " đ";
    }

    @SuppressLint("DefaultLocale")
    public String getTotalPriceFormated(){
        return "Tổng cộng  " + String.format("%1$,.0f", Float.parseFloat(price)) + " đ";
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FoodOrder> getListFood() {
        return foodOrder;
    }

    public void setListFood(List<FoodOrder> foodOrder) {
        this.foodOrder = foodOrder;
    }
}
