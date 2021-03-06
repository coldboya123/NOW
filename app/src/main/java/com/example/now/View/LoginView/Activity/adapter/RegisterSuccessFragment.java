package com.example.now.View.LoginView.Activity.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.databinding.FragmentRegisterSuccessBinding;

public class RegisterSuccessFragment extends Fragment {

    private FragmentRegisterSuccessBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterSuccessBinding.inflate(inflater, container, false);

        event();

        return binding.getRoot();
    }

    private void event() {
        binding.btnLogin.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
            getParentFragmentManager().popBackStack();
        });
    }
}