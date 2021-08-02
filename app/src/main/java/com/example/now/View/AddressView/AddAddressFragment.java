package com.example.now.View.AddressView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.now.R;
import com.example.now.Repository.AddressRepository;
import com.example.now.ViewModel.AddressViewModel;
import com.example.now.databinding.FragmentAddAddressBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class AddAddressFragment extends Fragment {

    private FragmentAddAddressBinding binding;
    private AddressViewModel viewModel;
    private String token;
    private final int HOUSE = 0, COMPANY = 1;
    private int type = -1;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddAddressBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }

    private void mapView() {
        token = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");

        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new AddressViewModel(new AddressRepository());
            }
        }.create(AddressViewModel.class);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void event(){
        binding.typeHouse.setOnClickListener(v -> {
            binding.typeHouse.setBackground(requireActivity().getDrawable(R.drawable.background_btnlogout));
            binding.typeHouse.setTextColor(requireActivity().getColor(R.color.primary_color));
            binding.typeCompany.setBackground(requireActivity().getDrawable(R.drawable.outline_background));
            binding.typeCompany.setTextColor(requireActivity().getColor(R.color.gray));
            type = HOUSE;
        });

        binding.typeCompany.setOnClickListener(v -> {
            binding.typeCompany.setBackground(requireActivity().getDrawable(R.drawable.background_btnlogout));
            binding.typeCompany.setTextColor(requireActivity().getColor(R.color.primary_color));
            binding.typeHouse.setBackground(requireActivity().getDrawable(R.drawable.outline_background));
            binding.typeHouse.setTextColor(requireActivity().getColor(R.color.gray));
            type = COMPANY;
        });

        binding.btnAdd.setOnClickListener(v -> {
            if (!validateField()){
                Toast.makeText(getContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                observeData();
            }
        });

        binding.btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }

    private void observeData(){
        JSONObject object = new JSONObject();
        try {
            object.put("function", "addUserAddress");
            object.put("token", token);
            object.put("address", binding.txtAddress.getText().toString());
            object.put("title", type==0 ? "Nhà" : "Công ty");
            object.put("name", binding.txtName.getText().toString());
            object.put("phone", binding.txtPhone.getText().toString());
            object.put("note", binding.txtNote.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewModel.addUserAddress(object.toString())
                .observe(getViewLifecycleOwner(), responseData -> {
                    if (responseData.getResult().equals("1")){
                        getParentFragmentManager().popBackStack();
                    } else {
                        Toast.makeText(getContext(), "Thêm địa chỉ không thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validateField(){
        return !binding.txtAddress.getText().toString().isEmpty() && !binding.txtName.getText().toString().isEmpty()
                && !binding.txtPhone.getText().toString().isEmpty() && type != -1;
    }
}