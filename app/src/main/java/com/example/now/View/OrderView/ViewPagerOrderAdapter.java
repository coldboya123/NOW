package com.example.now.View.OrderView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.now.View.OrderView.TabView.ComingFragment;
import com.example.now.View.OrderView.TabView.OrderHistoryFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerOrderAdapter extends FragmentPagerAdapter {
    public ViewPagerOrderAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ComingFragment();
            case 1:
                return new OrderHistoryFragment();
        }
        return null;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Đang đến";
            case 1:
                return "Lịch sử";
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
