package com.example.now.View.AddressView.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.R;
import com.example.now.View.AddressView.AddAddress.view.AddAddressFragment;
import com.example.now.View.AddressView.Address.AddressFragment;
import com.example.now.View.UserView.ChangePassword.ChangePwdFragment;
import com.example.now.View.UserView.Profile.UserProfileFragment;
import com.example.now.databinding.ActivityAddressBinding;

public class AddressActivity extends AppCompatActivity {

    private ActivityAddressBinding binding;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView();
    }

    private void mapView() {
        String page = getIntent().getStringExtra("page");
        getIntent().putExtra("check", true);
        fragmentManager = getSupportFragmentManager();
        switch (page){
            case "address":
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .add(R.id.addressContainer, new AddressFragment(), "addressFragment")
                        .addToBackStack("addressBackStack")
                        .commit();
                break;
            case "userProfile":
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .add(R.id.addressContainer, new UserProfileFragment(), "userProfileFragment")
                        .addToBackStack("userProfileBackStack")
                        .commit();
                break;
        }

    }

    public void onClickAddAddress(){
        AddAddressFragment addAddressFragment = new AddAddressFragment();
        AddressFragment addressFragment = (AddressFragment) fragmentManager.findFragmentByTag("addressFragment");
        if (addressFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(addressFragment)
                    .add(R.id.addressContainer, addAddressFragment, "addAddressFragment")
                    .addToBackStack("addAddressBackStack")
                    .commit();
        }
    }

    public void onClickChangePassword(){
        ChangePwdFragment changePwdFragment = new ChangePwdFragment();
        UserProfileFragment userProfileFragment = (UserProfileFragment) fragmentManager.findFragmentByTag("userProfileFragment");
        if (userProfileFragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(userProfileFragment)
                    .add(R.id.addressContainer, changePwdFragment, "changePwdFragment")
                    .addToBackStack("changePwdBackStack")
                    .commit();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}