package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.ResponseData;

import retrofit2.Call;

public class PaymentRepository {
    private APIRequest apiRequest;

    public PaymentRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<ResponseData> processOrder(String request){
        return apiRequest.processOrder(request);
    }
}
