package com.example.now.Model.Object;

import com.example.now.Model.Constant.Constant;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Food implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("shop_id")
    private String shopId;
    @SerializedName("parent_id")
    private String parentId;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("special_price")
    private String specialPrice;
    @SerializedName("num_selled")
    private String numSelled;
    @SerializedName("image")
    private String image;
    @SerializedName("detail")
    private String detail;
    @SerializedName("status")
    private String status;
    @SerializedName("tag")
    private String tag;
    @SerializedName("shop")
    private String shop;
    private int numBuy;

    public void setNumBuy(int numBuy) {
        this.numBuy = numBuy;
    }

    public int getNumBuy() {
        return numBuy;
    }

    public Food(String id, String shopId, String parentId, String name, String price, String specialPrice, String numSelled, String image, String detail, String status, String tag, int numBuy) {
        this.id = id;
        this.shopId = shopId;
        this.parentId = parentId;
        this.name = name;
        this.price = price;
        this.specialPrice = specialPrice;
        this.numSelled = numSelled;
        this.image = image;
        this.detail = detail;
        this.status = status;
        this.tag = tag;
        this.numBuy = numBuy;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", shopId='" + shopId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", specialPrice='" + specialPrice + '\'' +
                ", numSelled='" + numSelled + '\'' +
                ", image='" + image + '\'' +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                ", tag='" + tag + '\'' +
                ", shop='" + shop + '\'' +
                ", numBuy=" + numBuy +
                '}';
    }

    public Food(String id, String shopId, String parentId, String name, String price, String specialPrice, String numSelled, String image, String detail, String status, String tag, String shop, int numBuy) {
        this.id = id;
        this.shopId = shopId;
        this.parentId = parentId;
        this.name = name;
        this.price = price;
        this.specialPrice = specialPrice;
        this.numSelled = numSelled;
        this.image = image;
        this.detail = detail;
        this.status = status;
        this.tag = tag;
        this.shop = shop;
        this.numBuy = numBuy;
    }

    public String getId() {
        return id;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getPriceFormated() {
        return String.format("%1$,.0f", Float.parseFloat(price)) + " đ";
    }

    public String getTotalPriceFormated() {
        return specialPrice.equals("-1") ? String.format("%1$,.0f", Float.parseFloat(price) * numBuy) + " đ" : String.format("%1$,.0f", Float.parseFloat(specialPrice) * numBuy) + " đ";
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecialPrice() {
        return specialPrice;
    }

    public String getSpecialPriceFormated() {
        return String.format("%1$,.0f", Float.parseFloat(specialPrice)) + " đ";
    }

    public void setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getNumSelled() {
        return numSelled;
    }

    public String getNumSelledFormated() {
        return numSelled.equals("0")? numSelled : numSelled + " đã bán";
    }

    public void setNumSelled(String numSelled) {
        this.numSelled = numSelled;
    }

    public String getImage() {
        return Constant.SERVER_URL + "now/image/" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
