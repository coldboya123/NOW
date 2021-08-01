package com.example.now.View.Payment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.now.Model.Object.Food;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_Payment_Adapter extends RecyclerView.Adapter<RCV_Payment_Adapter.ViewHolder> {

    private List<Food> list;
    private Context context;

    public RCV_Payment_Adapter(List<Food> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_payment_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Food food = list.get(position);
        Glide.with(context).load(food.getImage()).into(holder.imageView);
        holder.txtName.setText(food.getNumBuy() + " x " + food.getName());
        holder.txtPrice.setText(food.getTotalPriceFormated());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtName, txtPrice;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            txtName = itemView.findViewById(R.id.name);
            txtPrice = itemView.findViewById(R.id.price);
        }
    }
}
