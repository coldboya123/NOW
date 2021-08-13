package com.example.now.View.UserView.ChangePassword;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.now.R;
import com.example.now.Repository.UserRepository;
import com.example.now.ViewModel.UserViewModel;
import com.example.now.databinding.FragmentChangePwdBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangePwdFragment extends Fragment {

    private FragmentChangePwdBinding binding;
    private UserViewModel viewModel;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangePwdBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }

    private void mapView() {
        token = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new UserViewModel(new UserRepository());
            }
        }.create(UserViewModel.class);
    }

    private void event() {
        binding.btnSave.setOnClickListener(v -> {
            if (binding.txtpwd.getText().toString().isEmpty() || binding.txtnewpwd.getText().toString().isEmpty() || binding.txtrepwd.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                if (!binding.txtnewpwd.getText().toString().equals(binding.txtrepwd.getText().toString())) {
                    Toast.makeText(getContext(), "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("function", "changePassword");
                        object.put("token", token);
                        object.put("oldpassword", binding.txtpwd.getText().toString());
                        object.put("newpassword", binding.txtnewpwd.getText().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    viewModel.changePassword(object.toString())
                            .observe(getViewLifecycleOwner(), responseData -> {
                                Log.d("bbb", "event: " + responseData.toString());
                                switch (responseData.getResult()) {
                                    case "1":
                                        Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                        getParentFragmentManager().popBackStack();
                                        break;
                                    case "-1":
                                        Toast.makeText(getContext(), "Đổi mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
                                        break;
                                    case "0":
                                        Toast.makeText(getContext(), "Mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            });
                }
            }
        });

        binding.btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }
}