package com.example.now.Model.Object;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("id")
    private String id;
    @SerializedName("shop_id")
    private String shopId;
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
    @SerializedName("name")
    private String name;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", shopId='" + shopId + '\'' +
                ", userId='" + userId + '\'' +
                ", listFood='" + listFood + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                ", ship='" + ship + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Order(String id, String shopId, String userId, String listFood, String time, String status, String price, String ship, String name) {
        this.id = id;
        this.shopId = shopId;
        this.userId = userId;
        this.listFood = listFood;
        this.time = time;
        this.status = status;
        this.price = price;
        this.ship = ship;
        this.name = name;
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
