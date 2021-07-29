package com.example.now.View.LoginView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.R;
import com.example.now.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private onClickRegister onClickRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }

    private void mapView(){
//        fragmentManager = getChildFragmentManager();
    }

    private void event() {
        binding.btnRegister.setOnClickListener(v -> {
            onClickRegister.onClickRegister();
        });
    }

    public void setOnClickRegisterListener(onClickRegister onClickRegisterListener){
        this.onClickRegister = onClickRegisterListener;
    }
}