package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.Shop;
import com.example.now.Repository.SavedRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedViewModel extends ViewModel {
    private SavedRepository repository;
    private final MutableLiveData<List<Shop>> shopListLiveData;

    public SavedViewModel(SavedRepository repository){
        this.repository = repository;
        shopListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Shop>> getShopSaved(String request){
        repository.getShopSaved(request)
                .enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                        shopListLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Shop>> call, Throwable t) {
                        Log.d("bbb", "get shop saved onFailure: " + t.getMessage());
                    }
                });
        return shopListLiveData;
    }
}
