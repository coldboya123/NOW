package com.example.now.View.HomeView.HomeContent.adapter.TabFood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Object.TabFood;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_TabFood_Adapter extends RecyclerView.Adapter<RCV_TabFood_Adapter.ViewHolder> {

    private Context context;
    private List<TabFood> list;
    private onSelectedTab handler;

    public RCV_TabFood_Adapter(List<TabFood> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_tabfood_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        TabFood tabFood = list.get(position);
        holder.imageView.setImageResource(tabFood.getImg());
        holder.textView.setText(tabFood.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnSelectTab(onSelectedTab handler){
        this.handler = handler;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            LinearLayout linearLayout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);
            linearLayout = itemView.findViewById(R.id.tabFoodItem);

            linearLayout.setOnClickListener(v -> {
                handler.onSelectTab(getLayoutPosition());
            });
        }
    }
}
