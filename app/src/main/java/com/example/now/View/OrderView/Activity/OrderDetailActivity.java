package com.example.now.View.OrderView.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.Order;
import com.example.now.Repository.OrderRepository;
import com.example.now.View.Payment.adapter.RCV_Payment_Adapter;
import com.example.now.ViewModel.OrderViewModel;
import com.example.now.databinding.ActivityOrderDetailBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private ActivityOrderDetailBinding binding;
    private Order order;
    private OrderViewModel viewModel;
    private JSONObject object;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView();
        observeData();
        event();
    }

    private void mapView() {
        token = getSharedPreferences("data", MODE_PRIVATE).getString("token", "");
        order = (Order) getIntent().getSerializableExtra("order");
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new OrderViewModel(new OrderRepository());
            }
        }.create(OrderViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    private void observeData(){
        object = new JSONObject();
        try {
            object.put("function", "getOrderDetail");
            object.put("token", token);
            object.put("order_id", order.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getOrderDetail(object.toString())
                .observe(this, orderDetail -> {
                    int numBuy = 0;
                    List<Food> foodList = new ArrayList<>();
                    for (int i = 0; i < orderDetail.getListFood().size(); i++) {
                        numBuy += Integer.parseInt(orderDetail.getListFood().get(i).getNumBuy());
                        orderDetail.getListFood().get(i).getFood().setNumBuy(Integer.parseInt(orderDetail.getListFood().get(i).getNumBuy()));
                        foodList.add(orderDetail.getListFood().get(i).getFood());
                    }
                    binding.shopName.setText(orderDetail.getShopName());
                    binding.price1.setText(orderDetail.getPriceFormated() + " - " + numBuy + " phần - Tiền mặt");
                    binding.name.setText(orderDetail.getName() + " - " + orderDetail.getPhone());
                    binding.address.setText(orderDetail.getAddress());
                    binding.btnShopName.setText(orderDetail.getShopName());
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                    binding.recyclerView.setAdapter(new RCV_Payment_Adapter(foodList));
                    binding.count.setText("Tổng (" + numBuy + "phần)");
                    binding.price2.setText(orderDetail.getPrice2Formated());
                    binding.totalPrice.setText(orderDetail.getTotalPriceFormated());
                });
    }

    private void event(){

        binding.btnShopName.setOnClickListener(v -> {

        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}