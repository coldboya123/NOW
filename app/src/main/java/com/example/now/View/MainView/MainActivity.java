package com.example.now.View.MainView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.now.R;
import com.example.now.View.HomeView.HomeFragment;
import com.example.now.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView();
        event();
    }

    private void mapView(){
        binding.viewPager.setAdapter(new ViewPagerMainAdapter(getSupportFragmentManager()));
        binding.viewPager.setOffscreenPageLimit(5);
    }

    private void event() {
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_home);
                        break;
                    case 1:
                        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_order);
                        break;
                    case 2:
                        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_saved);
                        break;
                    case 3:
                        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_noti);
                        break;
                    case 4:
                        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_user);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottomnavigation_home:
                    binding.viewPager.setCurrentItem(0);
                    item.setChecked(true);
                    break;
                case R.id.bottomnavigation_order:
                    binding.viewPager.setCurrentItem(1);
                    item.setChecked(true);
                    break;
                case R.id.bottomnavigation_saved:
                    binding.viewPager.setCurrentItem(2);
                    item.setChecked(true);
                    break;
                case R.id.bottomnavigation_noti:
                    binding.viewPager.setCurrentItem(3);
                    item.setChecked(true);
                    break;
                case R.id.bottomnavigation_user:
                    binding.viewPager.setCurrentItem(4);
                    item.setChecked(true);
                    break;
            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        Log.d("bbb", "onBackPressed: " + getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
//        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().getFragments().get(0);
//        if (homeFragment != null && homeFragment.getChildFragmentManager().getBackStackEntryCount()>1){
//            homeFragment.getChildFragmentManager().popBackStack();
//        }else {
//            super.onBackPressed();
//        }
    }
}