package com.example.now.Model.ApiService;

import com.example.now.Model.Object.Banner;
import com.example.now.Model.Object.Category;
import com.example.now.Model.Object.City;
import com.example.now.Model.Object.CityResponse;
import com.example.now.Model.Object.Collection;
import com.example.now.Model.Object.District;
import com.example.now.Model.Object.DistrictResponse;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Comment;
import com.example.now.Model.Object.GroupFood;
import com.example.now.Model.Object.Order;
import com.example.now.Model.Object.OrderDetail;
import com.example.now.Model.Object.RequestData;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Model.Object.Shop;
import com.example.now.Model.Object.User;
import com.example.now.Model.Object.UserAddress;
import com.example.now.Model.Object.Ward;
import com.example.now.Model.Object.WardResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Call<ResponseData> saveShop(@Body String request);

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

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<OrderDetail> getOrderDetail(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Shop>> getShopSaved(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<ResponseData> processComment(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Shop>> searchShop(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<List<Food>> searchFood(@Body String request);

    @Headers("Content-Type: application/json")
    @GET("/api/province/")
    Call<CityResponse> getCity();

    @Headers("Content-Type: application/json")
    @GET("/api/province/district/{cityID}")
    Call<DistrictResponse> getDistrict(@Path("cityID") String cityID);

    @Headers("Content-Type: application/json")
    @GET("/api/province/ward/{districtID}")
    Call<WardResponse> getWard(@Path("districtID") String districtID);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<User> getUserProfile(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<ResponseData> editUser(@Body String request);

    @Headers("Content-Type: application/json")
    @POST("/now/")
    Call<ResponseData> changePassword(@Body String request);
}
