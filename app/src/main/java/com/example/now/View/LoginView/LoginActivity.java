package com.example.now.View.LoginView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.now.R;
import com.example.now.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginFragment loginFragment;
    private FragmentManager fragmentManager;
    private RegisterFragment registerFragment;
    private RegisterSuccessFragment successFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView();
        event();
    }

    private void mapView() {
        fragmentManager = getSupportFragmentManager();
        loginFragment = new LoginFragment();
        fragmentManager.beginTransaction()
                .add(R.id.loginContainer, loginFragment, "loginFragment")
                .addToBackStack("loginBackStack")
                .commit();
        registerFragment = new RegisterFragment();
        successFragment = new RegisterSuccessFragment();
    }

    private void event() {
        loginFragment.setOnClickRegisterListener(() -> {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(loginFragment)
                    .add(R.id.loginContainer, registerFragment, "registerFragment")
                    .addToBackStack("registerBackStack")
                    .commit();
        });

        registerFragment.setOnClickRegisterListener(() -> {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(registerFragment)
                    .add(R.id.loginContainer, successFragment, "registerSuccessFragment")
                    .addToBackStack("registerSuccessBackStack")
                    .commit();
        });

    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getFragments().get(0) == successFragment){
            fragmentManager.popBackStack();
            fragmentManager.popBackStack();
        } else {
            if (fragmentManager.getBackStackEntryCount()>1){
                fragmentManager.popBackStack();
            } else {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
    }
}