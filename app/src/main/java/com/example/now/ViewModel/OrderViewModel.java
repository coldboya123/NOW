package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.Order;
import com.example.now.Repository.OrderRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private OrderRepository repository;
    private MutableLiveData<List<Order>> listOrderLiveData;

    public OrderViewModel(OrderRepository repository) {
        this.repository = repository;
        listOrderLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Order>> getOrder(String request) {
        repository.getOrder(request)
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        listOrderLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {
                        Log.d("bbb", "get Order onFailure: " + t.getMessage());
                    }
                });
        return listOrderLiveData;
    }
}
