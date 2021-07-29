package com.example.now.View.HomeView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.View.ShopView.ShopActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_HomeShop_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Shop> list;
    private final int ITEMVIEW = 0, LOADINGVIEW = 1;

    public RCV_HomeShop_Adapter(List<Shop> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v;
        switch (viewType){
            case ITEMVIEW:
                v = LayoutInflater.from(context).inflate(R.layout.rcv_shop_item, parent, false);
                return new ItemViewHolder(v);
            case LOADINGVIEW:
                v = LayoutInflater.from(context).inflate(R.layout.loading_layout, parent, false);
                return new LoadingViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            Shop shop = list.get(position);
            Glide.with(context).load(shop.getImage()).into(((ItemViewHolder) holder).imageView);
            ((ItemViewHolder) holder).name.setText(shop.getName());
            ((ItemViewHolder) holder).rate.setText(shop.getStar());
            ((ItemViewHolder) holder).voucherShip.setVisibility(shop.getVoucherShip().equals("0") ?View.GONE:View.VISIBLE);
            ((ItemViewHolder) holder).voucherPrice.setVisibility(shop.getVoucherPrice().equals("0") ?View.GONE:View.VISIBLE);
        } else {
            ((LoadingViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) != null ? ITEMVIEW : LOADINGVIEW;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        ImageView imageView;
        TextView name, rate, voucherPrice, voucherShip;
        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.shopImage);
            name = itemView.findViewById(R.id.shopName);
            rate = itemView.findViewById(R.id.shopRate);
            voucherPrice = itemView.findViewById(R.id.voucherPrice);
            voucherShip = itemView.findViewById(R.id.voucherShip);
            constraintLayout = itemView.findViewById(R.id.blockShop);

            constraintLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra("shop", list.get(getAdapterPosition()));
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.loading);
        }
    }
}
