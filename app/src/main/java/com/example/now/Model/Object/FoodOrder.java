package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

public class FoodOrder {
    @SerializedName("food")
    private Food food;
    @SerializedName("numBuy")
    private String numBuy;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getNumBuy() {
        return numBuy;
    }

    public void setNumBuy(String numBuy) {
        this.numBuy = numBuy;
    }
}
