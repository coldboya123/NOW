package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.ResponseData;
import com.example.now.Repository.LoginRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private LoginRepository repository;
    private MutableLiveData<ResponseData> registerLiveData;
    private MutableLiveData<ResponseData> loginLiveData;

    public LoginViewModel(LoginRepository repository){
        this.repository = repository;
        registerLiveData = new MutableLiveData<>();
        loginLiveData = new MutableLiveData<>();
    }

    public LiveData<ResponseData> Register(String request){
        repository.Register(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        registerLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "get register result onFailure: " + t.getMessage());
                    }
                });
        return registerLiveData;
    }

    public LiveData<ResponseData> Login(String request){
        repository.Login(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        loginLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "get login result onFailure: " + t.getMessage());
                    }
                });
        return loginLiveData;
    }
}
