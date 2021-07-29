package com.example.now.View.ShopView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.now.CustomComponent.AppBarStateChangeListener;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.View.HomeView.HomeFragment;
import com.example.now.View.ShopView.TabView.FoodFragment;
import com.example.now.View.ShopView.TabView.ShopOrderFragment;
import com.example.now.databinding.ActivityShopBinding;
import com.google.android.material.appbar.AppBarLayout;

public class ShopActivity extends AppCompatActivity {

    private ActivityShopBinding binding;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView();
        event();
    }


    private void mapView() {
        getIntent().putExtra("shop", getIntent().getSerializableExtra("shop"));
        ShopFragment shopFragment = new ShopFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.shopContainer, shopFragment, "shopFragment")
                .addToBackStack("shopBackStack")
                .commit();
    }

    private void event() {
//        new ShopFragment().setOnSelectFoodListener(id -> {
//            Log.d("bbb", "event: " + id);
//        });
    }

    public void onSelectFood(Food food){
        FoodFragment foodFragment = new FoodFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("food_id", food);
        foodFragment.setArguments(bundle);
        ShopFragment shopFragment = (ShopFragment) fragmentManager.findFragmentByTag("shopFragment");
        if (shopFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(shopFragment)
                    .add(R.id.shopContainer, foodFragment, "foodFragment")
                    .addToBackStack("foodBackStack")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("bbb", "onBackPressed: " + getSupportFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getBackStackEntryCount()>1){
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}