package com.example.now.View.AddressView.Address;

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
import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.Repository.AddressRepository;
import com.example.now.View.AddressView.Activity.AddressActivity;
import com.example.now.View.AddressView.adapter.RCV_ChooseAddress_Adapter;
import com.example.now.View.ShopView.Activity.ShopActivity;
import com.example.now.ViewModel.AddressViewModel;
import com.example.now.databinding.FragmentChooseAddressBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class AddressFragment extends Fragment {

    private FragmentChooseAddressBinding binding;
    private AddressViewModel viewModel;
    private RCV_ChooseAddress_Adapter adapter;
    private JSONObject object;
    private String token;
    private boolean check = false;

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
        //Vì sử dụng chung adapter với recyclerview của chọn địa chỉ khi thanh toán, sử dụng check để kiểm tra đang dùng adapter cho recyclerview nào
        check = requireActivity().getIntent().getBooleanExtra("check", false);
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
                    if (!check) {
                        adapter.setOnSelectAddressListener(address -> {
                            CartSingleton.getInstance().setSelectedAddress(address);
                            MyFragmentManager.getFragmentManager(getContext()).popBackStack();
                        });
                    }
                });
    }

    private void event() {
        binding.btnAddAddress.setOnClickListener(v -> {
            if (check){
                ((AddressActivity) requireActivity()).onClickAddAddress();
            } else {
                ((ShopActivity) requireActivity()).onClickAddAddress();
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            if (check) {
                requireActivity().finish();
                requireActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            } else {
                MyFragmentManager.getFragmentManager(getContext()).popBackStack();
            }
        });
    }

}