package com.example.now.View.HomeView.TabView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.Model.Constant.Constant;
import com.example.now.Model.Object.Shop;
import com.example.now.Repository.HomeRepository;
import com.example.now.View.Adapter.RCV_Shop_Adapter;
import com.example.now.View.HomeView.module.onTabLoading;
import com.example.now.ViewModel.HomeViewModel;
import com.example.now.databinding.FragmentHomeDrinkBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeDrinkFragment extends Fragment {

    private FragmentHomeDrinkBinding binding;
    private int page = 0;
    private final List<Shop> shopList = new ArrayList<>();
    private RCV_Shop_Adapter adapter;
    private onTabLoading handlerTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeDrinkBinding.inflate(inflater, container, false);

        mapView();

        return binding.getRoot();
    }

    private void mapView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new RCV_Shop_Adapter(shopList);
        binding.recyclerView.setAdapter(adapter);
    }

    private void observeData(){
        HomeViewModel viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new HomeViewModel(new HomeRepository());
            }
        }.create(HomeViewModel.class);
        JSONObject object = new JSONObject();
        try {
            object.put("function", "getHomeShop");
            object.put("type", Constant.DOUONG);
            object.put("page", ++page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getHomeShop(object.toString())
                .observe(getViewLifecycleOwner(), shops -> {
                    shopList.remove(shopList.size()-1);
                    adapter.notifyItemRemoved(shopList.size());
                    shopList.addAll(shops);
                    adapter.notifyItemRangeInserted(shopList.size() - shops.size(), shops.size());
                    Log.d("bbb", "loadmore: " + shopList.size());
                    handlerTab.onTabLoading(false);
                });
    }

    public void loadmore(){
        mapView();
        shopList.add(null);
        observeData();
    }

    public void setOnLoading(onTabLoading handlerTab){
        this.handlerTab = handlerTab;
    }
}