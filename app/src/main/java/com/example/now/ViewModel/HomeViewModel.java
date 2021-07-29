package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.Banner;
import com.example.now.Model.Object.Category;
import com.example.now.Model.Object.Collection;
import com.example.now.Model.Object.Shop;
import com.example.now.Repository.HomeRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private HomeRepository repository;
    private MutableLiveData<List<Banner>> bannersLiveData;
    private MutableLiveData<List<Collection>> collectionsLiveData;
    private MutableLiveData<List<Category>> cateLiveData;
    private MutableLiveData<List<Shop>> shopLiveData;
    public HomeViewModel(HomeRepository repository){
        this.repository = repository;
        bannersLiveData = new MutableLiveData<>();
        collectionsLiveData = new MutableLiveData<>();
        cateLiveData = new MutableLiveData<>();
        shopLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Banner>> getHomeBanner(String request){
        repository.getHomeBanner(request)
                .enqueue(new Callback<List<Banner>>() {
                    @Override
                    public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                        List<Banner> bannerList = response.body();
                        bannersLiveData.setValue(bannerList);
                    }

                    @Override
                    public void onFailure(Call<List<Banner>> call, Throwable t) {
                        Log.d("bbb", "get Home banner onFailure: " + t.getMessage());
                    }
                });
        return bannersLiveData;
    }

    public LiveData<List<Collection>> getHomeCollection(String request){
        repository.getHomeCollection(request)
                .enqueue(new Callback<List<Collection>>() {
                    @Override
                    public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                        List<Collection> collectionList = response.body();
                        collectionsLiveData.setValue(collectionList);
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {
                        Log.d("bbb", "get Home collection onFailure: " + t.getMessage());
                    }
                });
        return collectionsLiveData;
    }

    public LiveData<List<Category>> getHomeCategory(String request){
        repository.getHomeCategory(request)
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        List<Category> categoryList = response.body();
                        cateLiveData.setValue(categoryList);
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Log.d("bbb", "get Home collection onFailure: " + t.getMessage());
                    }
                });
        return cateLiveData;
    }

    public LiveData<List<Shop>> getHomeShop(String request){
        repository.getHomeShop(request)
                .enqueue(new Callback<List<Shop>>() {
                    @Override
                    public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                        List<Shop> shopList = response.body();
                        shopLiveData.setValue(shopList);
                    }

                    @Override
                    public void onFailure(Call<List<Shop>> call, Throwable t) {
                        Log.d("bbb", "get Home shop onFailure: " + t.getMessage());
                    }
                });
        return shopLiveData;
    }
}
