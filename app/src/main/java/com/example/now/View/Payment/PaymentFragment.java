package com.example.now.View.Payment;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.now.Model.ApiService.CartSingleton;
import com.example.now.Model.Object.Shop;
import com.example.now.Model.Object.UserAddress;
import com.example.now.R;
import com.example.now.Repository.PaymentRepository;
import com.example.now.Repository.ShopRepository;
import com.example.now.Repository.UserRepository;
import com.example.now.View.ShopView.ShopActivity;
import com.example.now.ViewModel.PaymentViewModel;
import com.example.now.ViewModel.ShopViewModel;
import com.example.now.ViewModel.UserViewModel;
import com.example.now.databinding.FragmentPaymentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentFragment extends Fragment {

    private FragmentPaymentBinding binding;
    private Shop shop;
    private int total = 0, numBonus = 0;
    private BottomSheetDialog dialog;
    private String note;
    private PaymentViewModel shopViewModel;
    private JSONObject object;
    private UserViewModel userViewModel;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);

        mapView();
        observeData();
        event();

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void mapView() {
        token = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");

        if (getArguments() != null) {
            shop = (Shop) getArguments().getSerializable("shop");
        }
        total = CartSingleton.getInstance().getTotal() + 30000;
        binding.shopName.setText(shop.getName());
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rcvCart.setAdapter(new RCV_Payment_Adapter(CartSingleton.getInstance().getCart()));
        binding.totalCount.setText("Tổng (" + CartSingleton.getInstance().getItemCount() + " phần)");
        binding.price.setText(CartSingleton.getInstance().getTotalFormated());
        binding.total.setText(formatPrice(total));

        shopViewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new PaymentViewModel(new PaymentRepository());
            }
        }.create(PaymentViewModel.class);

        userViewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new UserViewModel(new UserRepository());
            }
        }.create(UserViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    private void observeData(){
        object = new JSONObject();
        try {
            object.put("function", "getUserAddress");
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        userViewModel.getUserAddress(object.toString())
                .observe(getViewLifecycleOwner(), userAddresses -> {
                    for (int i = 0; i < userAddresses.size(); i++) {
                        if (userAddresses.get(i).getStatus().equals("1")){
                            UserAddress address = userAddresses.get(i);
                            binding.name.setText(address.getName() + " - " + address.getPhone());
                            binding.address.setText(address.getAddress());
                            break;
                        }
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void event() {
        binding.btnPlus.setOnClickListener(v -> {
            numBonus++;
            binding.numBonus.setText(numBonus + "");
            total += 5000;
            binding.total.setText(formatPrice(total));
        });

        binding.btnMinus.setOnClickListener(v -> {
            if (numBonus > 0){
                numBonus--;
                binding.numBonus.setText(numBonus + "");
                total -= 5000;
                binding.total.setText(formatPrice(total));
            }
        });

        binding.switchShipping.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                buttonView.setTextColor(getResources().getColor(R.color.primary_color, null));
                total += 5000;
                binding.total.setText(formatPrice(total));
            } else {
                buttonView.setTextColor(getResources().getColor(R.color.gray, null));
                total -= 5000;
                binding.total.setText(formatPrice(total));
            }
        });

        binding.btnNote.setOnClickListener(v -> {
            dialog = new BottomSheetDialog(requireContext());
            dialog.setContentView(R.layout.note_layout);
            dialog.show();
            ImageButton btnClose = dialog.findViewById(R.id.btnClose);
            EditText txtNote = dialog.findViewById(R.id.txtNote);
            Button btnDone = dialog.findViewById(R.id.btnDone);

            if (btnClose != null) {
                btnClose.setOnClickListener(v1 -> {
                    dialog.hide();
                });
            }
            if (btnDone != null && txtNote != null) {
                btnDone.setOnClickListener(v1 -> {
                    note = txtNote.getText().toString();
                    binding.btnNote.setText(note);
                    dialog.hide();
                });
            }
        });

        binding.btnOrder.setOnClickListener(v -> {
            String token = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");
            object = new JSONObject();
            try {
                object.put("function", "processOrder");
                object.put("token", token);
                object.put("shop_id", shop.getId());
                object.put("list_food", CartSingleton.getInstance().getListFoodId());
                object.put("totalprice", total);
                object.put("ship", 30000);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            shopViewModel.processOrder(object.toString())
                    .observe(getViewLifecycleOwner(), requestResult -> {
                        if (requestResult.getResult().equals("1")){
                            ((ShopActivity) requireActivity()).onClickOrder();
                            CartSingleton.getInstance().clearCart();
                        } else {
                            Toast.makeText(getContext(), requestResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        binding.btnBack.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

    private String formatPrice(int price){
        return String.format("%1$,.0f", (float) price) + " đ";
    }
}