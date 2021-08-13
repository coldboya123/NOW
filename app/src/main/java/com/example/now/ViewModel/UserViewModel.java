package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.ResponseData;
import com.example.now.Model.Object.User;
import com.example.now.Model.Object.UserAddress;
import com.example.now.Repository.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private UserRepository repository;
    private final MutableLiveData<User> userLiveData;
    private final MutableLiveData<ResponseData> editUserLiveData;
    private final MutableLiveData<ResponseData> changePwdLiveData;

    public UserViewModel(UserRepository userRepository){
        repository = userRepository;
        userLiveData = new MutableLiveData<>();
        editUserLiveData = new MutableLiveData<>();
        changePwdLiveData = new MutableLiveData<>();
    }

    public LiveData<User> getUserProfile(String request){
        repository.getUserProfile(request)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        userLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("bbb", "get user profile onFailure: " + t.getMessage());
                    }
                });
        return userLiveData;
    }

    public LiveData<ResponseData> editUser(String request){
        repository.editUser(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        editUserLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "edit user onFailure: " + t.getMessage());
                    }
                });
        return editUserLiveData;
    }

    public LiveData<ResponseData> changePassword(String request){
        repository.changePassword(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        changePwdLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "change password onFailure: " + t.getMessage());
                    }
                });
        return changePwdLiveData;
    }
}
