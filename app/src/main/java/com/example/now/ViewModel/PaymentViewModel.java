package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.ResponseData;
import com.example.now.Repository.PaymentRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentViewModel extends ViewModel {
    PaymentRepository repository;
    private final MutableLiveData<ResponseData> orderLiveData;

    public PaymentViewModel(PaymentRepository repository){
        this.repository = repository;
        orderLiveData = new MutableLiveData<>();
    }

    public LiveData<ResponseData> processOrder(String request) {
        repository.processOrder(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        orderLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "order onFailure: " + t.getMessage());
                    }
                });

        return orderLiveData;
    }
}
