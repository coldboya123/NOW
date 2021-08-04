package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.Order;
import com.example.now.Model.Object.OrderDetail;
import com.example.now.Model.Object.ResponseData;

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

    public Call<OrderDetail> getOrderDetail(String request){
        return apiRequest.getOrderDetail(request);
    }

    public Call<ResponseData> processComment(String request){
        return apiRequest.processComment(request);
    }
}
