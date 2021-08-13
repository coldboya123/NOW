package com.example.now.View.ShopView.Shop.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.now.View.ShopView.TabView.Comment.view.ShopCommentFragment;
import com.example.now.View.ShopView.TabView.ShopDetailFragment;
import com.example.now.View.ShopView.TabView.Order.view.ShopOrderFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerShopAdapter extends FragmentPagerAdapter {

    public ViewPagerShopAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ShopOrderFragment();
            case 1:
                return new ShopCommentFragment();
            case 2:
                return new ShopDetailFragment();
        }
        return null;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Đặt đơn";
            case 1:
                return "Bình luận";
            case 2:
                return "Thông tin";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
