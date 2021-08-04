package com.example.now.Model.Object;

import com.example.now.Model.Constant.Constant;
import com.google.gson.annotations.SerializedName;

public class Comment {


    @SerializedName("id")
    private String id;
    @SerializedName("shop_id")
    private String shopId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("comment")
    private String comment;
    @SerializedName("rate")
    private String rate;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("name")
    private String userName;
    @SerializedName("userImage")
    private String userImage;

    @Override
    public String toString() {
        return "FoodComment{" +
                "id='" + id + '\'' +
                ", foodId='" + shopId + '\'' +
                ", userId='" + userId + '\'' +
                ", comment='" + comment + '\'' +
                ", rate='" + rate + '\'' +
                ", datetime='" + datetime + '\'' +
                ", userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }

    public Comment(String id, String shopId, String userId, String comment, String rate, String datetime, String userName, String userImage) {
        this.id = id;
        this.shopId = shopId;
        this.userId = userId;
        this.comment = comment;
        this.rate = rate;
        this.datetime = datetime;
        this.userName = userName;
        this.userImage = userImage;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage == null ? Constant.SERVER_URL + "now/image/user/user.png" : Constant.SERVER_URL + "now/image/" + userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
