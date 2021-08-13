package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Comment;
import com.example.now.Model.Object.GroupFood;
import com.example.now.Model.Object.RequestData;
import com.example.now.Model.Object.ResponseData;
import com.example.now.Repository.ShopRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopViewModel extends ViewModel {
    private final ShopRepository repository;
    private final MutableLiveData<List<GroupFood>> groupFoodLiveData;
    private final MutableLiveData<Food> foodLiveData;
    private final MutableLiveData<List<Comment>> foodCommentLiveData;
    private final MutableLiveData<List<Comment>> shopCommentLiveData;
    private final MutableLiveData<ResponseData> saveShopLiveData;
    private final MutableLiveData<List<Food>> listFoodLiveData;

    public ShopViewModel(ShopRepository repository) {
        this.repository = repository;
        groupFoodLiveData = new MutableLiveData<>();
        foodLiveData = new MutableLiveData<>();
        foodCommentLiveData = new MutableLiveData<>();
        shopCommentLiveData = new MutableLiveData<>();
        saveShopLiveData = new MutableLiveData<>();
        listFoodLiveData = new MutableLiveData<>();
    }

    public LiveData<List<GroupFood>> getFoodbyShop(String request) {
        repository.getFoodbyShop(request)
                .enqueue(new Callback<List<GroupFood>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<GroupFood>> call, @NotNull Response<List<GroupFood>> response) {
                        List<GroupFood> list = response.body();
                        groupFoodLiveData.setValue(list);
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<GroupFood>> call, @NotNull Throwable t) {
                        Log.d("bbb", " get Group Food onFailure: " + t.getMessage());
                    }
                });
        return groupFoodLiveData;
    }

    public LiveData<Food> getFoodbyId(String request) {
        repository.getFoodbyId(request)
                .enqueue(new Callback<Food>() {
                    @Override
                    public void onResponse(@NotNull Call<Food> call, @NotNull Response<Food> response) {
                        foodLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NotNull Call<Food> call, @NotNull Throwable t) {
                        Log.d("bbb", "get Food by ID onFailure: " + t.getMessage());
                    }
                });
        return foodLiveData;
    }

    public LiveData<List<Comment>> getFoodComment(String request) {
        repository.getFoodComment(request)
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        foodCommentLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        Log.d("bbb", "get Food Comment onFailure: " + t.getMessage());
                    }
                });
        return foodCommentLiveData;
    }

    public LiveData<List<Comment>> getShopComment(String request) {
        repository.getShopComment(request)
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        shopCommentLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        Log.d("bbb", "get Shop Comment onFailure: " + t.getMessage());
                    }
                });
        return shopCommentLiveData;
    }

    public LiveData<ResponseData> saveShop(String request){
        repository.saveShop(request)
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        saveShopLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.d("bbb", "save shop onFailure: " + t.getMessage());
                    }
                });
        return saveShopLiveData;
    }

    public LiveData<List<Food>> searchFood(String request){
        repository.searchFood(request)
                .enqueue(new Callback<List<Food>>() {
                    @Override
                    public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                        listFoodLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Food>> call, Throwable t) {
                        Log.d("bbb", "search food onFailure: " + t.getMessage());
                    }
                });
        return listFoodLiveData;
    }
}
