package com.example.now.View.ShopView.TabView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.databinding.FragmentShopDetailBinding;

public class ShopDetailFragment extends Fragment {

    private FragmentShopDetailBinding binding;
    private Shop shop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShopDetailBinding.inflate(inflater, container, false);

        mapView();

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void mapView() {
        shop = (Shop) requireActivity().getIntent().getSerializableExtra("shop");
        binding.shopAddress.setText(shop.getAddress());
        binding.shopCate.setText("Shop/Cửa hàng");
        binding.shopPrice.setText("Giá trung bình: 50k");
        binding.shopOpenTime.setText(shop.getOpenTime() + " - " + shop.getCloseTime());
    }
}