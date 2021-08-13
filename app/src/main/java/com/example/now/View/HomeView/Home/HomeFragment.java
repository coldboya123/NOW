package com.example.now.View.HomeView.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.R;
import com.example.now.View.CategoryView.CategoryFragment;
import com.example.now.View.HomeView.HomeContent.view.HomeContentFragment;
import com.example.now.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private HomeContentFragment homeContentFragment;
    private CategoryFragment categoryFragment;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.example.now.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }


    private void mapView() {
        homeContentFragment = new HomeContentFragment();
        MyFragmentManager.addFragment(getContext(), R.id.homeContainer, homeContentFragment, "homecontentFragment");
    }

    private void event() {
        homeContentFragment.setOnSendCate(category -> {
            categoryFragment = new CategoryFragment();
            HomeContentFragment homeContentFragment = (HomeContentFragment) MyFragmentManager.getFragmentByTag(getContext(), "homecontentFragment");
            MyFragmentManager.addOtherFragment(getContext(), R.id.homeContainer, homeContentFragment, categoryFragment, "categoryFragment");
            categoryFragment.setData();

//            }
        });

    }

}