package com.example.now.Repository;

import com.example.now.Model.ApiService.APIRequest;
import com.example.now.Model.ApiService.RetrofitClient;
import com.example.now.Model.Object.City;
import com.example.now.Model.Object.CityResponse;
import com.example.now.Model.Object.District;
import com.example.now.Model.Object.DistrictResponse;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Model.Object.UserAddress;
import com.example.now.Model.Object.Ward;
import com.example.now.Model.Object.WardResponse;

import java.util.List;

import retrofit2.Call;

public class AddressRepository {
    private final APIRequest apiRequest;
    private final APIRequest apiRequestAddress;

    public AddressRepository(){
        apiRequest = RetrofitClient.getInstance().getAPIRequest();
        apiRequestAddress = RetrofitClient.getInstanceAddress().getAPIRequest();
    }

    public Call<List<UserAddress>> getUserAddress(String request){
        return apiRequest.getUserAddress(request);
    }

    public Call<ResponseData> addUserAddress(String request){
        return apiRequest.addUserAddress(request);
    }

    public Call<CityResponse> getCity(){
        return apiRequestAddress.getCity();
    }

    public Call<DistrictResponse> getDistrict(String cityID){
        return apiRequestAddress.getDistrict(cityID);
    }

    public Call<WardResponse> getWard(String districtID){
        return apiRequestAddress.getWard(districtID);
    }
}
