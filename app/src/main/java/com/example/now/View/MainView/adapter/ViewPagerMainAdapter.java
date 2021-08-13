package com.example.now.View.MainView.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.now.View.HomeView.Home.HomeFragment;
import com.example.now.View.NotiView.NotiFragment;
import com.example.now.View.OrderView.Order.view.OrderFragment;
import com.example.now.View.SavedView.SavedFragment;
import com.example.now.View.UserView.User.UserFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerMainAdapter extends FragmentPagerAdapter {

    public ViewPagerMainAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new OrderFragment();
            case 2:
                return new SavedFragment();
            case 3:
                return new NotiFragment();
            case 4:
                return new UserFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int getItemPosition(@NonNull @NotNull Object object) {
        return POSITION_NONE;
    }
}
