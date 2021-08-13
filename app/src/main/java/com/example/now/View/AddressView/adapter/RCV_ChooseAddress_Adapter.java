package com.example.now.View.AddressView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Object.UserAddress;
import com.example.now.R;
import com.example.now.View.AddressView.module.onSelectAddress;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RCV_ChooseAddress_Adapter extends RecyclerView.Adapter<RCV_ChooseAddress_Adapter.ViewHolder> {

    private List<UserAddress> list;
    private Context context;
    private com.example.now.View.AddressView.module.onSelectAddress onSelectAddress;

    public RCV_ChooseAddress_Adapter(List<UserAddress> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public RCV_ChooseAddress_Adapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_address_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RCV_ChooseAddress_Adapter.ViewHolder holder, int position) {
        UserAddress address = list.get(position);
        holder.type.setText(address.getTitle());
        holder.address.setText(address.getAddress());
        holder.name.setText(address.getName());
        holder.phone.setText((address.getPhone()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, address, name, phone;
        ConstraintLayout blockAddress;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            blockAddress = itemView.findViewById(R.id.blockItem);

            blockAddress.setOnClickListener(v -> {
                UserAddress address = list.get(getAdapterPosition());
                if (onSelectAddress != null) {
                    onSelectAddress.onSelectAddress(address);
                }
            });
        }
    }

    public void setOnSelectAddressListener(onSelectAddress onSelectAddressListener) {
        this.onSelectAddress = onSelectAddressListener;
    }
}
