package com.example.now.View.ShopView.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.View.AddressView.AddAddress.view.AddAddressFragment;
import com.example.now.View.AddressView.Address.AddressFragment;
import com.example.now.View.MainView.view.MainActivity;
import com.example.now.View.Payment.view.PaymentFragment;
import com.example.now.View.ShopView.Food.FoodFragment;
import com.example.now.View.ShopView.OrderSuccess.OrderSuccessFragment;
import com.example.now.View.ShopView.SearchFood.view.SearchFoodFragment;
import com.example.now.View.ShopView.Shop.view.ShopFragment;
import com.example.now.databinding.ActivityShopBinding;

public class ShopActivity extends AppCompatActivity {

    private ActivityShopBinding binding;
    private FragmentManager fragmentManager;
    private Bundle bundle;

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
        fragmentManager = getSupportFragmentManager();
        MyFragmentManager.addFragment(this, R.id.shopContainer, new ShopFragment(), "shopFragment");;
    }

    private void event() {
//        new ShopFragment().setOnSelectFoodListener(id -> {
//            Log.d("bbb", "event: " + id);
//        });
    }

    public void onSelectFood(Shop shop, Food food) {
        FoodFragment foodFragment = new FoodFragment();
        bundle = new Bundle();
        bundle.putSerializable("food", food);
        bundle.putSerializable("shop", shop);
        foodFragment.setArguments(bundle);
        ShopFragment shopFragment = (ShopFragment) MyFragmentManager.getFragmentByTag(this,"shopFragment");
        if (shopFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, shopFragment,
                    foodFragment, "foodFragment");
        }
    }

    public void onSelectFoodSearch(Shop shop, Food food){
        FoodFragment foodFragment = new FoodFragment();
        bundle = new Bundle();
        bundle.putSerializable("food", food);
        bundle.putSerializable("shop", shop);
        foodFragment.setArguments(bundle);
        SearchFoodFragment searchFoodFragment = (SearchFoodFragment) MyFragmentManager.getFragmentByTag(this,"searchFoodFragment");
        if (searchFoodFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, searchFoodFragment,
                    foodFragment, "foodFragment");
        }
    }

    public void onClickDeliveryfromShopOrder(Shop shop) {
        PaymentFragment paymentFragment = new PaymentFragment();
        bundle = new Bundle();
        bundle.putSerializable("shop", shop);
        paymentFragment.setArguments(bundle);
        ShopFragment shopFragment = (ShopFragment) MyFragmentManager.getFragmentByTag(this,"shopFragment");
        if (shopFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, shopFragment,
                    paymentFragment, "paymentFragment");
        }
    }

    public void onClickDeliveryfromFood(Shop shop) {
        PaymentFragment paymentFragment = new PaymentFragment();
        bundle = new Bundle();
        bundle.putSerializable("shop", shop);
        paymentFragment.setArguments(bundle);
        FoodFragment foodFragment = (FoodFragment) MyFragmentManager.getFragmentByTag(this,"foodFragment");
        if (foodFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, foodFragment,
                    paymentFragment, "paymentFragment");
        }
    }

    public void onClickOrder() {
        PaymentFragment paymentFragment = (PaymentFragment) MyFragmentManager.getFragmentByTag(this,"paymentFragment");
        if (paymentFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, paymentFragment,
                    new OrderSuccessFragment(), "orderSuccessBackStack");

            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (Exception ignored) {
                } finally {
                    Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                    finish();
                }
            }).start();
        }
    }

    public void onClickChangeAddress() {
        PaymentFragment paymentFragment = (PaymentFragment) MyFragmentManager.getFragmentByTag(this,"paymentFragment");
        if (paymentFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, paymentFragment,
                    new AddressFragment(), "addressFragment");
        }
    }

    public void onClickAddAddress(){
        AddressFragment addressFragment = (AddressFragment) MyFragmentManager.getFragmentByTag(this,"addressFragment");
        if (addressFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, addressFragment,
                    new AddAddressFragment(), "addAddressFragment");
        }
    }

    public void onClickSearch(){
        ShopFragment shopFragment = (ShopFragment) MyFragmentManager.getFragmentByTag(this,"shopFragment");
        if (shopFragment != null) {
            MyFragmentManager.addOtherFragment(this, R.id.shopContainer, shopFragment,
                    new SearchFoodFragment(), "searchFoodFragment");
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}