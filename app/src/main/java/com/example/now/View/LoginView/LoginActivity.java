package com.example.now.View.LoginView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.now.R;
import com.example.now.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginFragment loginFragment;
    private FragmentManager fragmentManager;

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
    }

    private void event() {
        loginFragment.setOnClickRegisterListener(() -> {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .detach(loginFragment)
                    .add(R.id.loginContainer, new RegisterFragment(), "registerFragment")
                    .addToBackStack("registerBackStack")
                    .commit();
        });
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1){
            fragmentManager.popBackStack();
        } else {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}