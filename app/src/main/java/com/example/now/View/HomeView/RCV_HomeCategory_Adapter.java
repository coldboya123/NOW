package com.example.now.View.HomeView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Object.Category;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_HomeCategory_Adapter extends RecyclerView.Adapter<RCV_HomeCategory_Adapter.ViewHodler> {

    private Context context;
    private List<Category> list;
    private onSelectCategory handler;

    public RCV_HomeCategory_Adapter(List<Category> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_category_item, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHodler holder, int position) {
        Category category = list.get(position);
        holder.imageView.setImageResource(context.getResources().getIdentifier(category.getImage(), "drawable", context.getPackageName()));
        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView name;
        public ViewHodler(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cateImage);
            name = itemView.findViewById(R.id.cateName);
            linearLayout = itemView.findViewById(R.id.blockCate);

            linearLayout.setOnClickListener(v -> {
                handler.onSelectCategory(list.get(getAdapterPosition()));
            });
        }
    }

    public void setOnSelectCategory(onSelectCategory handler){
        this.handler = handler;
    }
}
