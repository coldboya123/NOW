package com.example.now.View.UserView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.now.R;
import com.example.now.View.LoginView.LoginActivity;
import com.example.now.View.MainView.MainActivity;
import com.example.now.databinding.FragmentUserBinding;

import java.util.Objects;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private SharedPreferences preferences;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }

    private void mapView() {
        preferences = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        if (token.isEmpty()) {
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.userName.setVisibility(View.GONE);
            binding.btnLogout.setVisibility(View.GONE);
        } else {
            binding.btnLogin.setVisibility(View.GONE);
            binding.userName.setVisibility(View.VISIBLE);
            binding.userName.setText(preferences.getString("name", ""));
            binding.btnLogout.setVisibility(View.VISIBLE);
        }
    }

    private void event() {
        binding.btnLogin.setOnClickListener(v -> {
            requireContext().startActivity(new Intent(getContext(), LoginActivity.class));
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        binding.btnLogout.setOnClickListener(v -> {
            preferences.edit().remove("token").remove("name").remove("id").apply();
            requireActivity().finish();
            requireActivity().startActivity(new Intent(requireContext(), MainActivity.class));
        });
    }
}