package com.example.now.View.OrderView.Order.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.View.OrderView.Order.adapter.ViewPagerOrderAdapter;
import com.example.now.databinding.FragmentOrderBinding;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false);

        mapView();

        return binding.getRoot();
    }

    private void mapView() {
        binding.viewPager.setAdapter(new ViewPagerOrderAdapter(getChildFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    public void notifiOrder(){
    }

}