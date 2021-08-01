package com.example.now.View.OrderView.TabView;

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
import com.example.now.Repository.OrderRepository;
import com.example.now.View.OrderView.RCV_Order_Adapter;
import com.example.now.ViewModel.OrderViewModel;
import com.example.now.databinding.FragmentComingBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class ComingFragment extends Fragment {

    private FragmentComingBinding binding;
    private OrderViewModel viewModel;
    private JSONObject object;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentComingBinding.inflate(inflater, container, false);

        mapView();
        observeData();

        return binding.getRoot();
    }

    private void mapView() {
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new OrderViewModel(new OrderRepository());
            }
        }.create(OrderViewModel.class);
    }

    private void observeData() {
        object = new JSONObject();
        try {
            object.put("function", "getOrder");
            object.put("status", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewModel.getOrder(object.toString())
                .observe(getViewLifecycleOwner(), orders -> {
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    binding.recyclerView.setAdapter(new RCV_Order_Adapter(orders));
                });
    }
}