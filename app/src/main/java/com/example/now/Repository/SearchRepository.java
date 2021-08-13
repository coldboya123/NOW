package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.Shop;

import java.util.List;

import retrofit2.Call;

public class SearchRepository {
    private APIRequest apiRequest;

    public SearchRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<List<Shop>> searchShop(String request){
        return apiRequest.searchShop(request);
    }
}
