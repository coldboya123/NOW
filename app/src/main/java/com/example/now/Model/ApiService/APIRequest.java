package com.example.now.Model.ApiService;

import com.example.now.Model.Object.Banner;
import com.example.now.Model.Object.Category;
import com.example.now.Model.Object.Collection;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Comment;
import com.example.now.Model.Object.GroupFood;
import com.example.now.Model.Object.Order;
import com.example.now.Model.Object.RequestData;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Model.Object.Shop;
import com.example.now.Model.Object.UserAddress;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIRequest {

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<ResponseData> Register(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<ResponseData> Login(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Banner>> getHomeBanner(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Category>> getHomeCategory(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Collection>> getHomeCollection(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Shop>> getHomeShop(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<GroupFood>> getFoodbyShop(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<Food> getFoodbybyId(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Comment>> getFoodComment(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Comment>> getShopComment(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<UserAddress>> getUserAddress(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<ResponseData> addUserAddress(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<ResponseData> processOrder(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Order>> getOrder(@Body String request);

}
