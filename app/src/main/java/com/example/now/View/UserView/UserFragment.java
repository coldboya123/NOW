package com.example.now.View.UserView;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.R;
import com.example.now.View.LoginView.LoginActivity;
import com.example.now.databinding.FragmentUserBinding;

import java.util.Objects;

public class UserFragment extends Fragment {

    FragmentUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);

        event();

        return binding.getRoot();
    }

    private void event() {
        binding.btnLogin.setOnClickListener(v -> {
            requireContext().startActivity(new Intent(getContext(), LoginActivity.class));
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}