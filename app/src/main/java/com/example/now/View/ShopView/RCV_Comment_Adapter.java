package com.example.now.View.ShopView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.now.CustomComponent.SvgRatingBar;
import com.example.now.Model.Object.Comment;
import com.example.now.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RCV_Comment_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Comment> list;

    public RCV_Comment_Adapter(List<Comment> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.food_nocomment_layout, parent, false);
                return new NullViewHolder(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.rcv_foodrating_item, parent, false);
                return new ItemViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? 0 : 1;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            Comment foodComment = list.get(position);
            Glide.with(context).load(foodComment.getUserImage()).into(((ItemViewHolder) holder).imageView);
            ((ItemViewHolder) holder).name.setText(foodComment.getUserName());
            ((ItemViewHolder) holder).datetime.setText(foodComment.getDatetime());
            ((ItemViewHolder) holder).comment.setText(foodComment.getComment());
            ((ItemViewHolder) holder).ratingBar.setRating(Float.parseFloat(foodComment.getRate()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView name, datetime, comment;
        SvgRatingBar ratingBar;
        public ItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userImage);
            name = itemView.findViewById(R.id.userName);
            datetime = itemView.findViewById(R.id.dateTime);
            comment = itemView.findViewById(R.id.foodComment);
            ratingBar = itemView.findViewById(R.id.foodRating);
        }
    }

    public class NullViewHolder extends RecyclerView.ViewHolder{

        public NullViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
