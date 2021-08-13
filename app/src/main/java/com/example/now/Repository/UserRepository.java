package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Model.Object.User;
import com.example.now.Model.Object.UserAddress;

import java.util.List;

import retrofit2.Call;

public class UserRepository {
    private final APIRequest apiRequest;

    public UserRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }

    public Call<User> getUserProfile(String request){
        return apiRequest.getUserProfile(request);
    }

    public Call<ResponseData> editUser(String request){
        return apiRequest.editUser(request);
    }

    public Call<ResponseData> changePassword(String request){
        return apiRequest.changePassword(request);
    }
}
