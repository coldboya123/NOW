package com.example.now.View.ShopView.TabView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.now.Model.ApiService.CartSingleton;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.Repository.ShopRepository;
import com.example.now.View.ShopView.RCV_GroupFood_Adapter;
import com.example.now.View.ShopView.ShopFragment;
import com.example.now.View.ShopView.onSelectFood;
import com.example.now.ViewModel.ShopViewModel;
import com.example.now.databinding.FragmentShopOrderBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShopOrderFragment extends Fragment {

    private FragmentShopOrderBinding binding;
    private ShopViewModel viewModel;
    private JSONObject object;
    private Shop shop;
    private RCV_GroupFood_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShopOrderBinding.inflate(inflater, container, false);

        mapView();
        notifyCartChange();
        observeData();

        return binding.getRoot();
    }

    private void mapView() {
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new ShopViewModel(new ShopRepository());
            }
        }.create(ShopViewModel.class);
    }

    private void observeData(){
        shop = (Shop) requireActivity().getIntent().getSerializableExtra("shop");
        List<Food> cart = CartSingleton.getInstance().getCart();
        //get Group Food
        object = new JSONObject();
        try {
            object.put("function", "getFoodbyShop");
            object.put("shop_id", shop.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getFoodbyShop(object.toString())
                .observe(getViewLifecycleOwner(), groupFoods -> {
                    for (int i = 0; i < groupFoods.size(); i++) {
                        List<Food> list = new ArrayList<>();
                        list = groupFoods.get(i).getListFood();
                        for (int j = 0; j < list.size(); j++) {
                            for (int k = 0; k < cart.size(); k++) {
                                if (cart.get(k).getId().equals(list.get(j).getId())){
                                    list.get(j).setNumBuy(cart.get(k).getNumBuy());
                                }
                            }
                        }

                    }
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    adapter = new RCV_GroupFood_Adapter(groupFoods);
                    binding.recyclerView.setAdapter(adapter);
                    adapter.setonSelectFoodListener(food -> {
                        ShopFragment shopFragment = (ShopFragment) getParentFragment();
                        if (shopFragment != null){
                            shopFragment.onSelectFood(food);
                        }
                    });
                    adapter.setOnClickButtonBuyListener(this::notifyCartChange);
                });
    }

    @SuppressLint("SetTextI18n")
    private void notifyCartChange(){
        if (CartSingleton.getInstance().getItemCount() > 0){
            binding.blockCart.setVisibility(View.VISIBLE);
            binding.itemCount.setText(CartSingleton.getInstance().getItemCount() + "");
            binding.total.setText(CartSingleton.getInstance().getTotalFormated());
        } else {
            binding.blockCart.setVisibility(View.GONE);
        }
    }

}