package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.Shop;

import java.util.List;

import retrofit2.Call;

public class SavedRepository {
    private APIRequest apiRequest;

    public SavedRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<List<Shop>> getShopSaved(String request){
        return apiRequest.getShopSaved(request);
    }
}
