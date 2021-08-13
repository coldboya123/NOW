package com.example.now.View.AddressView.AddAddress.adapter.ChooseDistrict;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Object.District;
import com.example.now.R;

import java.util.ArrayList;
import java.util.List;

public class RCV_AddressChooseDistrict_Adapter extends RecyclerView.Adapter<RCV_AddressChooseDistrict_Adapter.ViewHolder> {

    private List<District> list;
    private com.example.now.View.AddressView.AddAddress.adapter.ChooseDistrict.onSelectDistrict onSelectDistrict;

    public RCV_AddressChooseDistrict_Adapter(List<District> list) {
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
        holder.txt.setText(list.get(position).getDistrictName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setFilter(List<District> newList){
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
                if (onSelectDistrict!=null){
                    onSelectDistrict.onSelectDistrict(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setOnSelectDistrictListener(onSelectDistrict onSelectDistrictListener){
        this.onSelectDistrict = onSelectDistrictListener;
    }
}
