package com.example.now.Model.ApiService;

import com.example.now.Model.Object.Banner;
import com.example.now.Model.Object.Category;
import com.example.now.Model.Object.Collection;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.FoodComment;
import com.example.now.Model.Object.GroupFood;
import com.example.now.Model.Object.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIRequest {
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
    Call<List<FoodComment>> getFoodComment(@Body String request);

}
