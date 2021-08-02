package com.example.now.View.ShopView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.View.AddressView.AddAddressFragment;
import com.example.now.View.MainView.MainActivity;
import com.example.now.View.AddressView.ChooseAddressFragment;
import com.example.now.View.Payment.PaymentFragment;
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

    public void onSelectFood(Shop shop, Food food) {
        FoodFragment foodFragment = new FoodFragment();
        bundle = new Bundle();
        bundle.putSerializable("food", food);
        bundle.putSerializable("shop", shop);
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

    public void onClickDeliveryfromShopOrder(Shop shop) {
        PaymentFragment paymentFragment = new PaymentFragment();
        bundle = new Bundle();
        bundle.putSerializable("shop", shop);
        paymentFragment.setArguments(bundle);
        ShopFragment shopFragment = (ShopFragment) fragmentManager.findFragmentByTag("shopFragment");
        if (shopFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(shopFragment)
                    .add(R.id.shopContainer, paymentFragment, "paymentFragment")
                    .addToBackStack("shopBackStack")
                    .commit();
        }
    }

    public void onClickDeliveryfromFood(Shop shop) {
        PaymentFragment paymentFragment = new PaymentFragment();
        bundle = new Bundle();
        bundle.putSerializable("shop", shop);
        paymentFragment.setArguments(bundle);
        FoodFragment foodFragment = (FoodFragment) fragmentManager.findFragmentByTag("foodFragment");
        if (foodFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(foodFragment)
                    .add(R.id.shopContainer, paymentFragment, "paymentFragment")
                    .addToBackStack("paymentBackStack")
                    .commit();
        }
    }

    public void onClickOrder() {
        OrderSuccessFragment orderSuccessFragment = new OrderSuccessFragment();
        PaymentFragment paymentFragment = (PaymentFragment) fragmentManager.findFragmentByTag("paymentFragment");
        if (paymentFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(paymentFragment)
                    .add(R.id.shopContainer, orderSuccessFragment, "orderSuccessFragment")
                    .addToBackStack("orderSuccessBackStack")
                    .commit();

            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (Exception ignored) {
                } finally {
                    Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }).start();
        }
    }

    public void onClickChangeAddress() {
        ChooseAddressFragment chooseAddressFragment = new ChooseAddressFragment();
        PaymentFragment paymentFragment = (PaymentFragment) fragmentManager.findFragmentByTag("paymentFragment");
        if (paymentFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(paymentFragment)
                    .add(R.id.shopContainer, chooseAddressFragment, "chooseAddressFragment")
                    .addToBackStack("chooseAddressBackStack")
                    .commit();
        }
    }

    public void onClickAddAddress(){
        AddAddressFragment addAddressFragment = new AddAddressFragment();
        ChooseAddressFragment chooseAddressFragment = (ChooseAddressFragment) fragmentManager.findFragmentByTag("chooseAddressFragment");
        if (chooseAddressFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(chooseAddressFragment)
                    .add(R.id.shopContainer, addAddressFragment, "addAddressFragment")
                    .addToBackStack("addAddressBackStack")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("bbb", "onBackPressed: " + getSupportFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}