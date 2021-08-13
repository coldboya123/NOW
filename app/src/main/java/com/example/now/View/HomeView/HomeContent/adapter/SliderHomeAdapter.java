package com.example.now.View.HomeView.HomeContent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.now.Model.Object.Banner;
import com.example.now.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderHomeAdapter extends SliderViewAdapter<SliderHomeAdapter.SliderViewHolder> {

    private Context context;
    private List<Banner> list;

    public SliderHomeAdapter(Context context, List<Banner> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View  view = LayoutInflater.from(context).inflate(R.layout.slider_item, null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        Banner banner = list.get(position);
        Glide.with(context).load(banner.getImage()).into(viewHolder.sliderItem);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class SliderViewHolder extends com.smarteist.autoimageslider.SliderViewAdapter.ViewHolder{
        ImageView sliderItem;
        public SliderViewHolder(View itemView) {
            super(itemView);
            sliderItem = itemView.findViewById(R.id.sliderItem);
        }
    }
}
