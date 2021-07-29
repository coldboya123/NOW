package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.Banner;
import com.example.now.Model.Object.Category;
import com.example.now.Model.Object.Collection;
import com.example.now.Model.Object.Shop;

import java.util.List;

import retrofit2.Call;

public class HomeRepository {
    private APIRequest apiRequest;

    public HomeRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<List<Banner>> getHomeBanner(String request){
        return apiRequest.getHomeBanner(request);
    }

    public Call<List<Collection>> getHomeCollection(String request){
        return apiRequest.getHomeCollection(request);
    }

    public Call<List<Category>> getHomeCategory(String request){
        return apiRequest.getHomeCategory(request);
    }

    public Call<List<Shop>> getHomeShop(String request){
        return apiRequest.getHomeShop(request);
    }
}
