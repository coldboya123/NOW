package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.Shop;
import com.example.now.Repository.SearchRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    private SearchRepository repository;
    private final MutableLiveData<List<Shop>> shopLiveData;

    public SearchViewModel(SearchRepository repository){
        this.repository = repository;
        shopLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Shop>> searchShop(String request){
        repository.searchShop(request)
                .enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                        shopLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Shop>> call, Throwable t) {
                        Log.d("bbb", "search shop onFailure: " + t.getMessage());
                    }
                });
        return shopLiveData;
    }
}
