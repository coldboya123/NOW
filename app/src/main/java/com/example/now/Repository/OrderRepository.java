package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.Order;

import java.util.List;

import retrofit2.Call;

public class OrderRepository {
    private APIRequest apiRequest;

    public OrderRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<List<Order>> getOrder(String request){
        return apiRequest.getOrder(request);
    }
}
