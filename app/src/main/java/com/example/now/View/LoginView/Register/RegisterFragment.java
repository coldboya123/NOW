package com.example.now.View.LoginView.Register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.Repository.LoginRepository;
import com.example.now.View.LoginView.module.onClickRegister;
import com.example.now.ViewModel.LoginViewModel;
import com.example.now.databinding.FragmentRegisterBinding;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private LoginViewModel viewModel;
    private JSONObject object;
    private com.example.now.View.LoginView.module.onClickRegister onClickRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        event();

        return binding.getRoot();
    }

    private void event(){
        binding.btnRegister.setOnClickListener(v -> {
            if (checkEmpty()){
                observeData();
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            MyFragmentManager.popBackStack(getContext());
        });
    }

    private boolean checkEmpty(){
        if (binding.txtname.getText().toString().trim().isEmpty()){
            setError(binding.txtname);
            return false;
        } else if (binding.txtphone.getText().toString().trim().isEmpty()){
            setError(binding.txtphone);
            return false;
        } else if (binding.txtpwd.getText().toString().trim().isEmpty()){
            setError(binding.txtpwd);
            return false;
        } else if (binding.txtrepwd.getText().toString().trim().isEmpty()){
            setError(binding.txtrepwd);
            return false;
        } else if(!binding.txtpwd.getText().toString().trim().equals(binding.txtrepwd.getText().toString().trim())){
            binding.txtrepwd.setError("Mật khẩu không khớp");
            return false;
        }
        return true;
    }

    private void setError(TextInputEditText text){
        text.setError("Không được để trống");
    }

    private void mapView() {
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new LoginViewModel(new LoginRepository());
            }
        }.create(LoginViewModel.class);
    }

    private void observeData(){
        //register
        mapView();
        object = new JSONObject();
        try {
            object.put("function", "register");
            object.put("name", Objects.requireNonNull(binding.txtname.getText()).toString());
            object.put("phone", Objects.requireNonNull(binding.txtphone.getText()).toString());
            object.put("password", Objects.requireNonNull(binding.txtpwd.getText()).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.Register(object.toString())
                .observe(getViewLifecycleOwner(), loginResult -> {
                    if (loginResult.getResult().equals("0")){
                        binding.txtphone.setError(loginResult.getMessage());
                        Toast.makeText(getContext(), loginResult.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        onClickRegister.onClickRegister();
                    }
                });

    }

    public void setOnClickRegisterListener(onClickRegister onClickRegisterListener){
        this.onClickRegister = onClickRegisterListener;
    }
}