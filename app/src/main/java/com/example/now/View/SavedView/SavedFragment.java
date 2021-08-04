package com.example.now.View.SavedView;

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

import com.example.now.R;
import com.example.now.Repository.SavedRepository;
import com.example.now.View.HomeView.RCV_HomeShop_Adapter;
import com.example.now.ViewModel.SavedViewModel;
import com.example.now.databinding.FragmentSavedBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class SavedFragment extends Fragment {

    private FragmentSavedBinding binding;
    private SavedViewModel viewModel;
    private String token;
    private JSONObject object;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSavedBinding.inflate(inflater, container, false);

        mapView();
        observeData();

        return binding.getRoot();
    }

    private void mapView() {
        token = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new SavedViewModel(new SavedRepository());
            }
        }.create(SavedViewModel.class);
    }

    private void observeData(){
        object = new JSONObject();
        try {
            object.put("function", "getShopSaved");
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewModel.getShopSaved(object.toString())
                .observe(getViewLifecycleOwner(), shops -> {
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    binding.recyclerView.setAdapter(new RCV_HomeShop_Adapter(shops));
                });
    }
}