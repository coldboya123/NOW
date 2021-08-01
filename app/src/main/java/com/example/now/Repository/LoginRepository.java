package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.ResponseData;

import retrofit2.Call;

public class LoginRepository {
    private APIRequest apiRequest;

    public  LoginRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<ResponseData> Register(String request){
        return apiRequest.Register(request);
    }

    public Call<ResponseData> Login(String request){
        return apiRequest.Login(request);
    }
}
