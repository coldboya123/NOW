package com.example.now.View.AddressView.AddAddress.adapter.ChooseWard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.now.Model.Object.Ward;
import com.example.now.R;

import java.util.ArrayList;
import java.util.List;

public class RCV_AddressChooseWard_Adapter extends RecyclerView.Adapter<RCV_AddressChooseWard_Adapter.ViewHolder> {

    private List<Ward> list;
    private com.example.now.View.AddressView.AddAddress.adapter.ChooseWard.onSelectWard onSelectWard;

    public RCV_AddressChooseWard_Adapter(List<Ward> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_chooseaddress_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt.setText(list.get(position).getWardName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setFilter(List<Ward> newList) {
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);

            txt.setOnClickListener(v -> {
                if (onSelectWard !=null){
                    onSelectWard.onSelectWard(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setOnSelectWardListener(onSelectWard onSelectWardListener){
        this.onSelectWard = onSelectWardListener;
    }
}
