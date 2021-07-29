package com.example.now.View.ShopView;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.now.Model.ApiService.CartSingleton;
import com.example.now.Model.Object.Food;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_Food_Adapter extends RecyclerView.Adapter<RCV_Food_Adapter.ViewHolder> {

    private List<Food> list;
    private Context context;
    private onSelectFood handler;
    private onClickButtonBuy onClickButtonBuy;

    public RCV_Food_Adapter(List<Food> list) {
        this.list = list;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Food food = list.get(position);

        if (food.getNumBuy() > 0){
            holder.btnMinus.setVisibility(View.VISIBLE);
            holder.numBuy.setVisibility(View.VISIBLE);
            holder.numBuy.setText(food.getNumBuy() + "");
        }

        Glide.with(context).load(food.getImage()).into(holder.imageView);
        holder.name.setText(food.getName());
        if (food.getName().equals("")) {
            holder.detail.setVisibility(View.GONE);
        } else {
            holder.detail.setText(food.getName());
        }
        if (food.getNumSelled().equals("0")) {
            holder.numSelled.setVisibility(View.GONE);
        } else {
            holder.numSelled.setText(food.getNumSelledFormated());
        }
        holder.price.setText(food.getPriceFormated());
        if (food.getSpecialPrice().equals("-1")) {
            holder.specialPrice.setVisibility(View.GONE);
        } else {
            holder.specialPrice.setText(food.getSpecialPriceFormated());
            holder.specialPrice.setPaintFlags(holder.specialPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout blockFood;
        ImageView imageView;
        TextView name, detail, numSelled, numBuy, price, specialPrice;
        ImageButton btnPlus, btnMinus;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            blockFood = itemView.findViewById(R.id.blockFood);
            imageView = itemView.findViewById(R.id.foodImage);
            name = itemView.findViewById(R.id.foodName);
            detail = itemView.findViewById(R.id.foodDetail);
            numSelled = itemView.findViewById(R.id.foodNumSelled);
            numBuy = itemView.findViewById(R.id.numBuy);
            price = itemView.findViewById(R.id.foodPrice);
            specialPrice = itemView.findViewById(R.id.foodSpecialPrice);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);

            blockFood.setOnClickListener(v -> {
                handler.onSelectFood(list.get(getAdapterPosition()));
            });

            btnPlus.setOnClickListener(v -> {
                int numBuy = Integer.parseInt(this.numBuy.getText().toString());
                if (numBuy == 0) {
                    this.numBuy.setVisibility(View.VISIBLE);
                    btnMinus.setVisibility(View.VISIBLE);
                }
                numBuy ++;
                this.numBuy.setText(numBuy + "");
                list.get(getAdapterPosition()).setNumBuy(numBuy);
                CartSingleton.getInstance().pushFood(list.get(getAdapterPosition()));
                onClickButtonBuy.onClickButtonBuy();
            });

            btnMinus.setOnClickListener(v -> {
                int numBuy = Integer.parseInt(this.numBuy.getText().toString());
                if (numBuy == 1) {
                    this.numBuy.setVisibility(View.INVISIBLE);
                    btnMinus.setVisibility(View.INVISIBLE);
                }
                numBuy --;
                this.numBuy.setText(numBuy + "");
                list.get(getAdapterPosition()).setNumBuy(numBuy);
                CartSingleton.getInstance().removeFood(list.get(getAdapterPosition()));
                onClickButtonBuy.onClickButtonBuy();
            });
        }
    }

    public void setOnSelectFoodListener(onSelectFood onSelectFoodListener) {
        this.handler = onSelectFoodListener;
    }

    public void setOnClickButtonBuyListener(onClickButtonBuy onClickButtonBuyListener){
        this.onClickButtonBuy = onClickButtonBuyListener;
    }
}
