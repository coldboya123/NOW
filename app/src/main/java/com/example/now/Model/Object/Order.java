package com.example.now.Model.Object;

import android.annotation.SuppressLint;

import com.example.now.Model.Constant.Constant;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("shop_id")
    private String shopId;
    @SerializedName("address")
    private String shopAddress;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("list_food")
    private String listFood;
    @SerializedName("time")
    private String time;
    @SerializedName("status")
    private String status;
    @SerializedName("price")
    private String price;
    @SerializedName("ship")
    private String ship;
    @SerializedName("rate")
    private String rate;
    @SerializedName("name")
    private String name;
    @SerializedName("count")
    private String count;
    @SerializedName("image")
    private String image;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", userId='" + userId + '\'' +
                ", listFood='" + listFood + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                ", ship='" + ship + '\'' +
                ", rate='" + rate + '\'' +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Order(String id, String shopId, String shopAddress, String userId, String listFood, String time, String status, String price, String ship, String rate, String name, String count, String image) {
        this.id = id;
        this.shopId = shopId;
        this.shopAddress = shopAddress;
        this.userId = userId;
        this.listFood = listFood;
        this.time = time;
        this.status = status;
        this.price = price;
        this.ship = ship;
        this.rate = rate;
        this.name = name;
        this.count = count;
        this.image = image;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public float getRateFormated() {
        return Float.parseFloat(rate);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return Constant.SERVER_URL + "now/image/" + image;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public String getCountFormated() {
        return "(" + count + "phần)";
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getIdFormated() {
        return "#" + id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListFood() {
        return listFood;
    }

    public void setListFood(String listFood) {
        this.listFood = listFood;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    @SuppressLint("DefaultLocale")
    public String getPriceFormated() {
        return String.format("%1$,.0f", Float.parseFloat(price)) + " đ";
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }
}
