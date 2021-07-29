package com.example.now.Model.Object;

import com.example.now.Model.Constant.Constant;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shop implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("cate")
    private String cate;
    @SerializedName("address")
    private String address;
    @SerializedName("star")
    private String star;
    @SerializedName("status")
    private String status;
    @SerializedName("open_time")
    private String openTime;
    @SerializedName("close_time")
    private String closeTime;
    @SerializedName("voucher_ship")
    private String voucherShip;
    @SerializedName("voucher_price")
    private String voucherPrice;

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", cate='" + cate + '\'' +
                ", address='" + address + '\'' +
                ", star='" + star + '\'' +
                ", status='" + status + '\'' +
                ", openTime='" + openTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", voucherShip='" + voucherShip + '\'' +
                ", voucherPrice='" + voucherPrice + '\'' +
                '}';
    }

    public Shop(String id, String name, String image, String cate, String address, String star, String status, String openTime, String closeTime, String voucherShip, String voucherPrice) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.cate = cate;
        this.address = address;
        this.star = star;
        this.status = status;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.voucherShip = voucherShip;
        this.voucherPrice = voucherPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Constant.SERVER_URL + "now/image/" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getVoucherShip() {
        return voucherShip;
    }

    public void setVoucherShip(String voucherShip) {
        this.voucherShip = voucherShip;
    }

    public String getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(String voucherPrice) {
        this.voucherPrice = voucherPrice;
    }
}
