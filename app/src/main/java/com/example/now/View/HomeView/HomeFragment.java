package com.example.now.View.HomeView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.R;
import com.example.now.View.CategoryView.CategoryFragment;
import com.example.now.View.HomeView.HomeContent.HomeContentFragment;
import com.example.now.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FragmentManager fragmentManager;
    //    private FragmentTransaction transaction;
    private HomeContentFragment homeContentFragment;
    private CategoryFragment categoryFragment;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }


    private void mapView() {
        fragmentManager = getChildFragmentManager();
        homeContentFragment = new HomeContentFragment();
        fragmentManager.beginTransaction().
                add(R.id.homeContainer, homeContentFragment, "homecontentFragment")
                .addToBackStack("homecontentBackStack")
                .commit();

    }

    private void event() {
        homeContentFragment.setOnSendCate(category -> {
            Log.d("bbb", "event: " + category.toString());
            categoryFragment = new CategoryFragment();
            HomeContentFragment homeContentFragment = (HomeContentFragment) fragmentManager.findFragmentByTag("homecontentFragment");
            if (homeContentFragment != null) {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .detach(homeContentFragment)
                        .add(R.id.homeContainer, categoryFragment, "categoryFragment")
                        .addToBackStack("categoryBackStack")
                        .commit();
                categoryFragment.setData(category);
                Log.d("bbb", "backstack: " + fragmentManager.getBackStackEntryCount());

            }
        });
    }

}