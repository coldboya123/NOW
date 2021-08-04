package com.example.now.View.ShopView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.Repository.ShopRepository;
import com.example.now.View.ShopView.TabView.ShopOrderFragment;
import com.example.now.ViewModel.ShopViewModel;
import com.example.now.databinding.FragmentShopBinding;
import com.google.android.material.appbar.AppBarLayout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;
    private ShopViewModel viewModel;
    private JSONObject object;
    private String token;
    private Shop shop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShopBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment

        mapView();
        event();


        return binding.getRoot();
    }

    private void mapView() {
        token = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");
        shop = (Shop) requireActivity().getIntent().getSerializableExtra("shop");
        binding.shopName.setText(shop.getName());
        Glide.with(this)
                .load(shop.getImage())
                .into(binding.imageShop);
        binding.viewPager.setAdapter(new ViewPagerShopAdapter(getChildFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        requireActivity().getIntent().putExtra("shop", shop);

        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new ShopViewModel(new ShopRepository());
            }
        }.create(ShopViewModel.class);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void event() {

        binding.appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                //  Collapsed
                binding.searchBar.setVisibility(View.VISIBLE);
                binding.btnSearch.setVisibility(View.INVISIBLE);
            } else {
                //Expanded
                binding.searchBar.setVisibility(View.INVISIBLE);
                binding.btnSearch.setVisibility(View.VISIBLE);
            }
        });

        binding.btnSave.setOnClickListener(v -> {
            object = new JSONObject();
            try {
                object.put("function", "saveShop");
                object.put("token", token);
                object.put("id", shop.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            viewModel.saveShop(object.toString())
                    .observe(getViewLifecycleOwner(), responseData -> {
                        switch (responseData.getResult()) {
                            case "1":
                                binding.btnSave.setBackground(requireActivity().getDrawable(R.drawable.ic_heart_full));
                                break;
                            case "0":
                                binding.btnSave.setBackground(requireActivity().getDrawable(R.drawable.ic_heart_white));
                                break;
                            case "-1":
                                Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    });
        });
    }

    public void onSelectFood(Shop shop, Food food) {
        ((ShopActivity) requireActivity()).onSelectFood(shop, food);
    }

    public void onClickDelivery(Shop shop) {
        ((ShopActivity) requireActivity()).onClickDeliveryfromShopOrder(shop);
    }
}