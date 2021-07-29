package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.FoodComment;
import com.example.now.Model.Object.GroupFood;

import java.util.List;

import retrofit2.Call;

public class ShopRepository {
    private APIRequest apiRequest;

    public ShopRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<List<GroupFood>> getFoodbyShop(String request){
        return apiRequest.getFoodbyShop(request);
    }

    public Call<Food> getFoodbyId(String request){
        return apiRequest.getFoodbybyId(request);
    }

    public Call<List<FoodComment>> getFoodComment(String request){
        return apiRequest.getFoodComment(request);
    }

}
