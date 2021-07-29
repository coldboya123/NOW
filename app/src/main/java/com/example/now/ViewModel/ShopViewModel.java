package com.example.now.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.FoodComment;
import com.example.now.Model.Object.GroupFood;
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
    private final MutableLiveData<List<FoodComment>> commentLiveData;

    public ShopViewModel(ShopRepository repository){
        this.repository = repository;
        groupFoodLiveData = new MutableLiveData<>();
        foodLiveData = new MutableLiveData<>();
        commentLiveData = new MutableLiveData<>();
    }

    public LiveData<List<GroupFood>> getFoodbyShop(String request){
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

    public LiveData<Food> getFoodbyId(String request){
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

    public LiveData<List<FoodComment>> getFoodComment(String request){
        repository.getFoodComment(request)
                .enqueue(new Callback<List<FoodComment>>() {
                    @Override
                    public void onResponse(Call<List<FoodComment>> call, Response<List<FoodComment>> response) {
                        commentLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<FoodComment>> call, Throwable t) {
                        Log.d("bbb", "get Food Comment onFailure: " + t.getMessage());
                    }
                });
        return commentLiveData;
    }
}
