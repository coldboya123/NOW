package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.UserAddress;
import com.example.now.Repository.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private UserRepository repository;
    private MutableLiveData<List<UserAddress>> addressLiveData;

    public UserViewModel(UserRepository repository){
        this.repository = repository;
        addressLiveData = new MutableLiveData<>();
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
}
