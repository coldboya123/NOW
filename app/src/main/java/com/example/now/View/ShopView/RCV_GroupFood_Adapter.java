package com.example.now.View.ShopView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Object.GroupFood;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_GroupFood_Adapter extends RecyclerView.Adapter<RCV_GroupFood_Adapter.ViewHolder> {

    private List<GroupFood> list;
    private Context context;
    private onSelectFood handler;
    private onClickButtonBuy onClickButtonBuy;

    public RCV_GroupFood_Adapter(List<GroupFood> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_foodgroup_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        GroupFood groupFood = list.get(position);
        holder.groupName.setText(groupFood.getSubcateName());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        RCV_Food_Adapter adapter = new RCV_Food_Adapter(groupFood.getListFood());
        holder.recyclerView.setAdapter(adapter);
        adapter.setOnSelectFoodListener(food -> {
            handler.onSelectFood(food);
        });
        adapter.setOnClickButtonBuyListener(() -> {
            onClickButtonBuy.onClickButtonBuy();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.groupName);
            recyclerView = itemView.findViewById(R.id.rcvGroupFood);
        }
    }

    public void setonSelectFoodListener(onSelectFood onSelectFood){
        this.handler = onSelectFood;
    }

    public void setOnClickButtonBuyListener(onClickButtonBuy onClickButtonBuyListener){
        this.onClickButtonBuy = onClickButtonBuyListener;
    }
}
