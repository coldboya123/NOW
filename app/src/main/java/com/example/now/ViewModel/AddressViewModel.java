package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.City;
import com.example.now.Model.Object.CityResponse;
import com.example.now.Model.Object.District;
import com.example.now.Model.Object.DistrictResponse;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Model.Object.UserAddress;
import com.example.now.Model.Object.Ward;
import com.example.now.Model.Object.WardResponse;
import com.example.now.Repository.AddressRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressViewModel extends ViewModel {
    private final AddressRepository repository;
    private final MutableLiveData<List<UserAddress>> addressLiveData;
    private final MutableLiveData<ResponseData> addAddressLiveData;
    private final MutableLiveData<CityResponse> cityLiveData;
    private final MutableLiveData<DistrictResponse> districtLiveData;
    private final MutableLiveData<WardResponse> wardLiveData;

    public AddressViewModel(AddressRepository repository){
        this.repository = repository;
        addressLiveData = new MutableLiveData<>();
        addAddressLiveData = new MutableLiveData<>();
        cityLiveData = new MutableLiveData<>();
        districtLiveData = new MutableLiveData<>();
        wardLiveData = new MutableLiveData<>();
    }

    public LiveData<List<UserAddress>> getUserAddress(String request){
        repository.getUserAddress(request)
                .enqueue(new Callback<List<UserAddress>>() {
                    @Override
                    public void onResponse(Call<List<UserAddress>> call, Response<List<UserAddress>> response) {
                        addressLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<UserAddress>> call, Throwable t) {
                        Log.d("bbb", "get user address onFailure: " + t.getMessage());
                    }
                });
        return addressLiveData;
    }

    public LiveData<ResponseData> addUserAddress(String request){
        repository.addUserAddress(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        addAddressLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "Add user address onFailure: " + t.getMessage());
                    }
                });
        return addAddressLiveData;
    }

    public LiveData<CityResponse> getCity(){
        repository.getCity()
                .enqueue(new Callback<CityResponse>() {
                    @Override
                    public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                        cityLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<CityResponse> call, Throwable t) {
                        Log.d("bbb", "get city onFailure: " + t.toString());
                    }
                });
        return cityLiveData;
    }

    public LiveData<DistrictResponse> getDistrict(String cityID){
        repository.getDistrict(cityID)
                .enqueue(new Callback<DistrictResponse>() {
                    @Override
                    public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                        districtLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<DistrictResponse> call, Throwable t) {
                        Log.d("bbb", "get District onFailure: " + t.toString());
                    }
                });
        return districtLiveData;
    }
    public LiveData<WardResponse> getWard(String districtID){
        repository.getWard(districtID)
                .enqueue(new Callback<WardResponse>() {
                    @Override
                    public void onResponse(Call<WardResponse> call, Response<WardResponse> response) {
                        wardLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<WardResponse> call, Throwable t) {
                        Log.d("bbb", "get Ward onFailure: " + t.toString());
                    }
                });
        return wardLiveData;
    }
}
