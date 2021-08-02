package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.UserAddress;

import java.util.List;

import retrofit2.Call;

public class UserRepository {
    private APIRequest apiRequest;

    public UserRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
    }


}
