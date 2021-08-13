package com.example.now.View.ShopView.Food;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.now.Model.ApiService.CartSingleton;
import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.Repository.ShopRepository;
import com.example.now.View.LoginView.Activity.view.LoginActivity;
import com.example.now.View.ShopView.Activity.ShopActivity;
import com.example.now.ViewModel.ShopViewModel;
import com.example.now.databinding.FragmentFoodBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class FoodFragment extends Fragment {

    private FragmentFoodBinding binding;
    private Food food;
    private Shop shop;
    private ShopViewModel viewModel;
    private JSONObject object;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(inflater, container, false);

        mapView();
        notifyCartChange();
        observeData();
        event();

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void mapView() {
        if (getArguments() != null) {
            food = (Food) getArguments().getSerializable("food");
            shop = (Shop) getArguments().getSerializable("shop");
        }

        if (food.getNumBuy() > 0) {
            binding.btnMinus.setVisibility(View.VISIBLE);
            binding.numBuy.setVisibility(View.VISIBLE);
            binding.numBuy.setText(food.getNumBuy() + "");
        }

        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new ShopViewModel(new ShopRepository());
            }
        }.create(ShopViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    private void observeData() {

        //get food by id

        binding.shopName.setText(food.getShop());
        Glide.with(requireContext()).load(food.getImage()).into(binding.foodImage);
        binding.foodName.setText(food.getName());
        binding.foodDetail.setText(food.getDetail());
        binding.foodNumSelled.setText(food.getNumSelledFormated());
        binding.foodPrice.setText(food.getPriceFormated());
        if (food.getSpecialPrice().equals("-1")) {
            binding.foodSpecialPrice.setVisibility(View.GONE);
        } else {
            binding.foodSpecialPrice.setText(food.getSpecialPriceFormated());
            binding.foodSpecialPrice.setPaintFlags(binding.foodSpecialPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        //get Food Comment
//        object = new JSONObject();
//        try {
//            object.put("function", "getFoodComment");
//            object.put("id", food.getId());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        viewModel.getFoodComment(object.toString())
//                .observe(getViewLifecycleOwner(), foodComments -> {
//                    if (foodComments.size() == 0) {
//                        foodComments.add(null);
//                    }
//                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
//                    binding.recyclerView.setAdapter(new RCV_Comment_Adapter(foodComments));
//                });
    }

    @SuppressLint("SetTextI18n")
    private void event() {
        binding.btnPlus.setOnClickListener(v -> {
            int numBuy = Integer.parseInt(binding.numBuy.getText().toString());
            if (numBuy == 0) {
                binding.numBuy.setVisibility(View.VISIBLE);
                binding.btnMinus.setVisibility(View.VISIBLE);
            }
            binding.numBuy.setText(++numBuy + "");
            food.setNumBuy(numBuy);
            CartSingleton.getInstance().pushFood(food);
            notifyCartChange();
        });

        binding.btnMinus.setOnClickListener(v -> {
            int numBuy = Integer.parseInt(binding.numBuy.getText().toString());
            if (numBuy == 1) {
                binding.numBuy.setVisibility(View.INVISIBLE);
                binding.btnMinus.setVisibility(View.INVISIBLE);
            }
            binding.numBuy.setText(--numBuy + "");
            food.setNumBuy(numBuy);
            CartSingleton.getInstance().removeFood(food);
            notifyCartChange();
        });

        binding.btnBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });

        binding.btnDelivery.setOnClickListener(v -> {
            SharedPreferences preferences = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE);
            if (preferences.getString("token", "").isEmpty()) {
                startActivity(new Intent(requireContext(), LoginActivity.class));
                requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                ((ShopActivity) requireActivity()).onClickDeliveryfromFood(shop);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void notifyCartChange() {
        if (CartSingleton.getInstance().getItemCount() > 0) {
            binding.blockCart.setVisibility(View.VISIBLE);
            binding.itemCount.setText(CartSingleton.getInstance().getItemCount() + "");
            binding.total.setText(CartSingleton.getInstance().getTotalFormated());
        } else {
            binding.blockCart.setVisibility(View.GONE);
        }
    }
}