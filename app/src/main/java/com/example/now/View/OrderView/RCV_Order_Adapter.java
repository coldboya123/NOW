package com.example.now.View.OrderView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Object.Order;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_Order_Adapter extends RecyclerView.Adapter<RCV_Order_Adapter.ViewHolder> {

    List<Order> list;
    Context context;

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

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Order order = list.get(position);
        holder.id.setText(order.getIdFormated());
        holder.name.setText(order.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, address, price, count, status, btnReOrder;
        ImageView imageView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.orderID);
            name = itemView.findViewById(R.id.shopName);
            address = itemView.findViewById(R.id.shopAddress);
            price = itemView.findViewById(R.id.price);
            count = itemView.findViewById(R.id.count);
            status = itemView.findViewById(R.id.status);
            btnReOrder = itemView.findViewById(R.id.btnReOrder);
            imageView = itemView.findViewById(R.id.image);

            btnReOrder.setOnClickListener(v -> {

            });
        }
    }
}
