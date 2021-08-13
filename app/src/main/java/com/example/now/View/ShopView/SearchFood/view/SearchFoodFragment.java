package com.example.now.View.ShopView.SearchFood.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.now.Model.ApiService.CartSingleton;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Shop;
import com.example.now.Repository.ShopRepository;
import com.example.now.View.ShopView.Activity.ShopActivity;
import com.example.now.View.ShopView.SearchFood.adapter.RCV_Food_Adapter;
import com.example.now.View.ShopView.module.onClickButtonBuy;
import com.example.now.ViewModel.ShopViewModel;
import com.example.now.databinding.FragmentSearchFoodBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SearchFoodFragment extends Fragment {

    private FragmentSearchFoodBinding binding;
    private ShopViewModel viewModel;
    private JSONObject object;
    private final Handler handler = new Handler();
    private Runnable runnable;
    private List<Food> cart;
    private Shop shop;
    private RCV_Food_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchFoodBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }

    private void mapView() {
        LinearLayout linearLayout1 = (LinearLayout) binding.searchView.getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3.getChildAt(0);
        autoComplete.setTextSize(15);
        binding.searchView.setOnClickListener(v1 -> {
            binding.searchView.setIconified(false);
        });

        shop = (Shop) requireActivity().getIntent().getSerializableExtra("shop");
        cart = CartSingleton.getInstance().getCart();

        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new ShopViewModel(new ShopRepository());
            }
        }.create(ShopViewModel.class);
        object = new JSONObject();
        try {
            object.put("function", "searchFood");
            object.put("id", shop.getId());
            object.put("queue", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.searchFood(object.toString())
                .observe(getViewLifecycleOwner(), foods -> {
                    for (int j = 0; j < foods.size(); j++) {
                        for (int k = 0; k < cart.size(); k++) {
                            if (cart.get(k).getId().equals(foods.get(j).getId())) {
                                foods.get(j).setNumBuy(cart.get(k).getNumBuy());
                            }
                        }
                    }
                    binding.image.setVisibility(foods.size() > 0 ? View.GONE : View.VISIBLE);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    binding.recyclerView.setAdapter(new RCV_Food_Adapter(foods));
                });
    }

    private void event() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacks(runnable);

                runnable = () -> {
                    object = new JSONObject();
                    try {
                        object.put("function", "searchFood");
                        object.put("id", shop.getId());
                        object.put("queue", newText);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    viewModel.searchFood(object.toString())
                            .observe(getViewLifecycleOwner(), foods -> {
                                binding.image.setVisibility(foods.size() > 0 ? View.GONE : View.VISIBLE);
                                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                                adapter = new RCV_Food_Adapter(foods);
                                binding.recyclerView.setAdapter(adapter);
                                adapter.setOnSelectFoodSearchListener(food -> {
                                    Log.d("bbb", "onQueryTextChange: " + food.toString());
                                    ((ShopActivity) requireActivity()).onSelectFoodSearch(shop, food);
                                });
                                adapter.setOnClickButtonBuyListener(new onClickButtonBuy() {
                                    @Override
                                    public void onClickButtonBuy() {

                                    }
                                });
                            });
                };
                handler.postDelayed(runnable, 1000);
                return false;
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }
}