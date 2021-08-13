package com.example.now.View.CategoryView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Constant.Constant;
import com.example.now.Model.Object.Category;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.Repository.HomeRepository;
import com.example.now.View.Adapter.RCV_Shop_Adapter;
import com.example.now.View.HomeView.HomeContent.adapter.RCV_HomeCollection_Adapter;
import com.example.now.View.SearchView.SearchActivity;
import com.example.now.ViewModel.HomeViewModel;
import com.example.now.databinding.FragmentCategoryBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private HomeViewModel viewModel;
    private JSONObject object;
    private int page = 1;
    private final List<Shop> shopList = new ArrayList<>();
    private RCV_Shop_Adapter adapter;
    private boolean isLast = false;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        mapView();
        observeData();
        event();

        return binding.getRoot();
    }

    private void mapView() {
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new HomeViewModel(new HomeRepository());
            }
        }.create(HomeViewModel.class);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void observeData() {
        //get Collection
        object = new JSONObject();
        try {
            object.put("function", "getCollection");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getHomeCollection(object.toString())
                .observe(getViewLifecycleOwner(), collections -> {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rcvCollection.getContext(),
                            layoutManager.getOrientation());
                    dividerItemDecoration.setDrawable(requireActivity().getDrawable(R.drawable.space_between_item));
                    binding.rcvCollection.addItemDecoration(dividerItemDecoration);
                    binding.rcvCollection.setLayoutManager(layoutManager);
                    binding.rcvCollection.setAdapter(new RCV_HomeCollection_Adapter(collections));
                });

        //get Shop
        object = new JSONObject();
        try {
            object.put("function", "getHomeShop");
            object.put("type", Constant.ALLFOOD);
            object.put("page", page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getHomeShop(object.toString())
                .observe(getViewLifecycleOwner(), shops -> {
                    shopList.addAll(shops);
                    adapter = new RCV_Shop_Adapter(shopList);
                    binding.rcvShop.setAdapter(adapter);
                    binding.rcvShop.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                });
    }

    private void event() {
        binding.nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY >= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())
                    && scrollY > oldScrollY && !isLast) {
                isLast = true;
                loadMore();
            }
        });

        binding.searchBar.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void loadMore(){
        mapView();
        shopList.add(null);
        adapter.notifyItemInserted(shopList.size()-1);
        object = new JSONObject();
        try {
            object.put("function", "getHomeShop");
            object.put("type", Constant.ALLFOOD);
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
                    isLast = false;
                });
    }

    public void setData() {
    }

}