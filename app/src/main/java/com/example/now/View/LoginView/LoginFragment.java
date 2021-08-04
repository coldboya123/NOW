package com.example.now.View.LoginView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.now.R;
import com.example.now.Repository.LoginRepository;
import com.example.now.View.MainView.MainActivity;
import com.example.now.ViewModel.LoginViewModel;
import com.example.now.databinding.FragmentLoginBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private onClickRegister onClickRegister;
    private LoginViewModel viewModel;
    private JSONObject object;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }

    private void mapView(){
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new LoginViewModel(new LoginRepository());
            }
        }.create(LoginViewModel.class);
    }

    private void event() {
        binding.btnLogin.setOnClickListener(v -> {
            object = new JSONObject();
            try {
                object.put("function", "login");
                object.put("phone", binding.txtPhone.getText().toString().trim());
                object.put("password", binding.txtPassword.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            viewModel.Login(object.toString())
                    .observe(getViewLifecycleOwner(), loginResult -> {
                        if (loginResult.getResult().isEmpty()) {
                            Toast.makeText(getContext(), "Số điện thoại hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                            sharedPreferences.edit()
                                    .putString("token", loginResult.getResult())
                                    .putString("name", loginResult.getName())
                                    .putString("id", loginResult.getId())
                                    .apply();
                            requireContext().startActivity(new Intent(requireContext(), MainActivity.class));
                            requireActivity().finish();
                        }
                    });
        });

        binding.btnRegister.setOnClickListener(v -> {
            onClickRegister.onClickRegister();
        });

        binding.btnBack.setOnClickListener(v -> {
            requireActivity().finish();
            requireActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    public void setOnClickRegisterListener(onClickRegister onClickRegisterListener){
        this.onClickRegister = onClickRegisterListener;
    }
}