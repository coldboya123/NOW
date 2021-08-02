package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Model.Object.UserAddress;

import java.util.List;

import retrofit2.Call;

public class AddressRepository {
    private APIRequest apiRequest;

    public AddressRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<List<UserAddress>> getUserAddress(String request){
        return apiRequest.getUserAddress(request);
    }

    public Call<ResponseData> addUserAddress(String request){
        return apiRequest.addUserAddress(request);
    }
}
