package com.example.now.View.OrderView.TabView.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.now.CustomComponent.SvgRatingBar;
import com.example.now.Model.Object.Order;
import com.example.now.R;
import com.example.now.Repository.OrderRepository;
import com.example.now.View.OrderView.Activity.OrderDetailActivity;
import com.example.now.ViewModel.OrderViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RCV_Order_Adapter extends RecyclerView.Adapter<RCV_Order_Adapter.ViewHolder> {

    private List<Order> list;
    private Context context;
    private BottomSheetDialog dialog;

    public RCV_Order_Adapter(List<Order> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_order_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Order order = list.get(position);
        holder.id.setText(order.getIdFormated());
        holder.name.setText(order.getName());
        holder.address.setText(order.getShopAddress());
        holder.price.setText(order.getPriceFormated());
        holder.count.setText(order.getCountFormated());
        switch (order.getStatus()) {
            case "0":
                holder.status.setText("Đang giao");
                break;
            case "1":
                holder.status.setText("Hoàn thành");
                break;
            case "-1":
                holder.status.setText("Đã huỷ");
                break;
        }
        Glide.with(context).load(order.getImage()).into(holder.imageView);
        if (order.getStatus().equals("0")) {
            holder.btnRating.setVisibility(View.GONE);
        } else {
            if (order.getRateFormated() == 0) {
                holder.btnRating.setVisibility(View.VISIBLE);
            } else {
                holder.btnRating.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, address, price, count, status, btnRating;
        ImageView imageView;
        ConstraintLayout blockItem;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.orderID);
            name = itemView.findViewById(R.id.shopName);
            address = itemView.findViewById(R.id.shopAddress);
            price = itemView.findViewById(R.id.price);
            count = itemView.findViewById(R.id.count);
            status = itemView.findViewById(R.id.status);
            btnRating = itemView.findViewById(R.id.btnRating);
            imageView = itemView.findViewById(R.id.image);
            blockItem = itemView.findViewById(R.id.blockItem);

            blockItem.setOnClickListener(v -> {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("order", list.get(getAdapterPosition()));
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });

            btnRating.setOnClickListener(v -> {
                dialog = new BottomSheetDialog(context);
                dialog.setContentView(R.layout.rating_layout);
                dialog.show();
                ImageButton btnClose = dialog.findViewById(R.id.btnClose);
                SvgRatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                EditText txtComment = dialog.findViewById(R.id.txtComment);
                Button btnDone = dialog.findViewById(R.id.btnDone);

                if (btnDone != null && ratingBar != null && txtComment != null) {
                    btnDone.setOnClickListener(v1 -> {
                        if (ratingBar.getRating() < 1) {
                            Toast.makeText(context, "Chưa đánh giá sao!", Toast.LENGTH_SHORT).show();
                        } else {
                            String token = context.getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");
                            JSONObject object = new JSONObject();
                            try {
                                object.put("function", "processRating");
                                object.put("token", token);
                                object.put("shop_id", list.get(getAdapterPosition()).getShopId());
                                object.put("order_id", list.get(getAdapterPosition()).getId());
                                object.put("rate", ratingBar.getRating());
                                object.put("comment", txtComment.getText().toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            OrderViewModel viewModel = new ViewModelProvider.Factory() {
                                @NonNull
                                @NotNull
                                @Override
                                public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                                    return (T) new OrderViewModel(new OrderRepository());
                                }
                            }.create(OrderViewModel.class);
                            viewModel.processComment(object.toString())
                                    .observe((LifecycleOwner) context, responseData -> {
                                        if (responseData.getResult().equals("1")) {
                                            Toast.makeText(context, "Đã gửi đánh giá!", Toast.LENGTH_SHORT).show();
                                            btnRating.setVisibility(View.GONE);
                                            dialog.hide();
                                        } else {
                                            Toast.makeText(context, "Đánh giá không thành công!", Toast.LENGTH_SHORT).show();
                                            Log.d("bbb", "on process rating failed : " + responseData.getMessage());
                                        }
                                    });
                        }

                        if (btnClose != null) {
                            btnClose.setOnClickListener(v2 -> {
                                dialog.hide();
                            });
                        }

                    });
                }
            });
        }
    }

}
