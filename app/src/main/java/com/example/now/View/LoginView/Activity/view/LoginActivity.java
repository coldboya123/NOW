package com.example.now.View.LoginView.Activity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.R;
import com.example.now.View.LoginView.Login.LoginFragment;
import com.example.now.View.LoginView.Register.RegisterFragment;
import com.example.now.View.LoginView.Activity.adapter.RegisterSuccessFragment;
import com.example.now.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginFragment loginFragment;
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
        loginFragment = new LoginFragment();
        MyFragmentManager.addFragment(this, R.id.loginContainer, loginFragment, "loginFragment");
        registerFragment = new RegisterFragment();
        successFragment = new RegisterSuccessFragment();
    }

    private void event() {
        loginFragment.setOnClickRegisterListener(() -> {
            MyFragmentManager.addOtherFragment(this, R.id.loginContainer, loginFragment,
                    registerFragment, "registerFragment");
        });

        registerFragment.setOnClickRegisterListener(() -> {
            MyFragmentManager.addOtherFragment(this, R.id.loginContainer, registerFragment,
                    successFragment, "registerSuccessFragment");
        });

    }

    @Override
    public void onBackPressed() {
        if (MyFragmentManager.getFragmentManager(this).getFragments().get(0) == successFragment){
            MyFragmentManager.popBackStack(this);
            MyFragmentManager.popBackStack(this);
        } else {
            if (MyFragmentManager.getBackStackEntryCount(this)>1){
                MyFragmentManager.popBackStack(this);
            } else {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
    }
}