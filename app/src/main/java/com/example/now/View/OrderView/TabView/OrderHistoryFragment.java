package com.example.now.View.OrderView.TabView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.R;
import com.example.now.databinding.FragmentOrderHistoryBinding;

public class OrderHistoryFragment extends Fragment {

    private FragmentOrderHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}