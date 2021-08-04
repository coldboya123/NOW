package com.example.now.View.MainView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.now.R;
import com.example.now.databinding.ActivityMainBinding;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAnalytics mFirebaseAnalytics;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView();
        event();
    }

    @SuppressLint("CommitPrefEdits")
    private void mapView() {
        id = getSharedPreferences("data", MODE_PRIVATE).getString("id", "");
        FirebaseMessaging.getInstance().subscribeToTopic(id);
//        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_order);
//        new Handler().post(() -> binding.viewPager.setCurrentItem(1));
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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
                switch (position) {
                    case 0:
                        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_home);
                        break;
                    case 1:
                        binding.bottomNavigation.setSelectedItemId(R.id.bottomnavigation_order);
                        Objects.requireNonNull(binding.viewPager.getAdapter()).notifyDataSetChanged();
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
            switch (item.getItemId()) {
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

//    public void onSelectOrderItem(Order order) {
//        OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("order", order);
//        orderDetailFragment.setArguments(bundle);
//        OrderFragment orderFragment = (OrderFragment) getSupportFragmentManager().getFragments().get(1);
//        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
//                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
//                .detach(orderFragment)
//                .add(R.id.viewPager, orderDetailFragment, "orderDetailFragment")
//                .addToBackStack("orderDetailBackStack")
//                .commit();
//    }

    @Override
    public void onBackPressed() {
        Log.d("bbb", "onBackPressed: " + getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().getFragments().get(0).getChildFragmentManager().popBackStack();
        } else {
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