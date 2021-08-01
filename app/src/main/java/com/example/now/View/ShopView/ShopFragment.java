package com.example.now.View.ShopView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.View.ShopView.TabView.ShopOrderFragment;
import com.example.now.databinding.FragmentShopBinding;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

public class ShopFragment extends Fragment {

    FragmentShopBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShopBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment

        mapView();
        event();


        return binding.getRoot();
    }

    private void mapView(){
        binding.viewPager.setAdapter(new ViewPagerShopAdapter(getChildFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        requireActivity().getIntent().putExtra("shop", requireActivity().getIntent().getSerializableExtra("shop"));
    }

    private void event(){

        binding.appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange())
            {
                //  Collapsed
                binding.searchBar.setVisibility(View.VISIBLE);
                binding.btnSearch.setVisibility(View.INVISIBLE);
            }
            else
            {
                //Expanded
                binding.searchBar.setVisibility(View.INVISIBLE);
                binding.btnSearch.setVisibility(View.VISIBLE);
            }
        });
    }

    public void onSelectFood(Shop shop, Food food){
        ((ShopActivity) requireActivity()).onSelectFood(shop, food);
    }

    public void onClickDelivery(Shop shop){
        ((ShopActivity) requireActivity()).onClickDeliveryfromShopOrder(shop);
    }
}