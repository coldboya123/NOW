package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.Order;
import com.example.now.Model.Object.OrderDetail;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Repository.OrderRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private final OrderRepository repository;
    private final MutableLiveData<List<Order>> listOrderLiveData;
    private final MutableLiveData<OrderDetail> orderDetailLiveData;
    private final MutableLiveData<ResponseData> ratingLiveData;

    public OrderViewModel(OrderRepository repository) {
        this.repository = repository;
        listOrderLiveData = new MutableLiveData<>();
        orderDetailLiveData = new MutableLiveData<>();
        ratingLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Order>> getOrder(String request) {
        repository.getOrder(request)
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<Order>> call, @NotNull Response<List<Order>> response) {
                        listOrderLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<Order>> call, @NotNull Throwable t) {
                        Log.d("bbb", "get Order onFailure: " + t.getMessage());
                    }
                });
        return listOrderLiveData;
    }

    public LiveData<OrderDetail> getOrderDetail(String request){
        repository.getOrderDetail(request)
                .enqueue(new Callback<OrderDetail>() {
                    @Override
                    public void onResponse(@NotNull Call<OrderDetail> call, @NotNull Response<OrderDetail> response) {
                        orderDetailLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NotNull Call<OrderDetail> call, @NotNull Throwable t) {
                        Log.d("bbb", "get order detail onFailure: " + t.getMessage());
                    }
                });
        return orderDetailLiveData;
    }

    public LiveData<ResponseData> processComment(String request){
        repository.processComment(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        ratingLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "process rating onFailure: " + t.getMessage());
                    }
                });
        return ratingLiveData;
    }
}
