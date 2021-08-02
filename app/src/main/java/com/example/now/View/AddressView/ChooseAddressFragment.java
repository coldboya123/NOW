package com.example.now.View.AddressView;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.Model.ApiService.CartSingleton;
import com.example.now.Repository.AddressRepository;
import com.example.now.Repository.UserRepository;
import com.example.now.View.ShopView.ShopActivity;
import com.example.now.ViewModel.AddressViewModel;
import com.example.now.ViewModel.UserViewModel;
import com.example.now.databinding.FragmentChooseAddressBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class ChooseAddressFragment extends Fragment {

    private FragmentChooseAddressBinding binding;
    private AddressViewModel viewModel;
    private RCV_ChooseAddress_Adapter adapter;
    private JSONObject object;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChooseAddressBinding.inflate(inflater, container, false);

        mapView();
        observeData();
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

    private void observeData() {
        object = new JSONObject();
        try {
            object.put("function", "getUserAddress");
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getUserAddress(object.toString())
                .observe(getViewLifecycleOwner(), addressList -> {
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    adapter = new RCV_ChooseAddress_Adapter(addressList);
                    binding.recyclerView.setAdapter(adapter);
                    adapter.setOnSelectAddressListener(address -> {
                        CartSingleton.getInstance().setSelectedAddress(address);
                        getParentFragmentManager().popBackStack();
                    });
                });
    }

    private void event() {
        binding.btnAddAddress.setOnClickListener(v -> {
            ((ShopActivity)requireActivity()).onClickAddAddress();
        });

        binding.btnBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

}