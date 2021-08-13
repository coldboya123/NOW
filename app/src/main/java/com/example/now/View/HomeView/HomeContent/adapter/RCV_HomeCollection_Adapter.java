package com.example.now.View.HomeView.HomeContent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import com.example.now.Model.Object.Collection;
import java.util.List;

public class RCV_HomeCollection_Adapter extends RecyclerView.Adapter<RCV_HomeCollection_Adapter.ViewHolder> {

    private Context context;
    private List<Collection> list;

    public RCV_HomeCollection_Adapter(List<Collection> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_collection_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Collection collection = list.get(position);
        Glide.with(context).load(collection.getImage()).into(holder.imageView);
        holder.name.setText(collection.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView name;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.collection_image);
            name = itemView.findViewById(R.id.collection_name);
        }
    }
}
