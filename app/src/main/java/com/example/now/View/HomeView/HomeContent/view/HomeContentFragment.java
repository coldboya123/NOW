package com.example.now.View.HomeView.HomeContent.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.Model.Object.TabFood;
import com.example.now.R;
import com.example.now.Repository.HomeRepository;
import com.example.now.View.HomeView.HomeContent.adapter.HomeCategory.RCV_HomeCategory_Adapter;
import com.example.now.View.HomeView.HomeContent.adapter.RCV_HomeCollection_Adapter;
import com.example.now.View.HomeView.HomeContent.adapter.SliderHomeAdapter;
import com.example.now.View.HomeView.HomeContent.adapter.TabFood.RCV_TabFood_Adapter;
import com.example.now.View.HomeView.TabView.HomeAllFragment;
import com.example.now.View.HomeView.TabView.HomeChickenFragment;
import com.example.now.View.HomeView.TabView.HomeDrinkFragment;
import com.example.now.View.HomeView.TabView.HomeFoodFragment;
import com.example.now.View.HomeView.TabView.HomeHotPotFragment;
import com.example.now.View.HomeView.TabView.HomePizzaFragment;
import com.example.now.View.SearchView.SearchActivity;
import com.example.now.ViewModel.HomeViewModel;
import com.example.now.databinding.FragmentHomeContentBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeContentFragment extends Fragment {
    private FragmentHomeContentBinding binding;
    private HomeViewModel viewModel;
    private RCV_TabFood_Adapter adapter;
    private int tabSelected = 0;
    private boolean isLast = false;
    private onSendCate handler;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeContentBinding.inflate(inflater, container, false);
        mapView();
        event();
        observeData();
        return binding.getRoot();
    }

    private void mapView() {
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new HomeViewModel(new HomeRepository());
            }
        }.create(HomeViewModel.class);

        List<TabFood> tabFoodList = new ArrayList<>();
        tabFoodList.add(new TabFood(R.drawable.ic_allfood, "Tất cả"));
        tabFoodList.add(new TabFood(R.drawable.ic_food, "Đồ ăn"));
        tabFoodList.add(new TabFood(R.drawable.ic_drink, "Thức uống"));
        tabFoodList.add(new TabFood(R.drawable.ic_pizza, "Pizza"));
        tabFoodList.add(new TabFood(R.drawable.ic_chicken, "Món gà"));
        tabFoodList.add(new TabFood(R.drawable.ic_hotpot, "Món lẩu"));

        binding.rcvTabFood.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapter = new RCV_TabFood_Adapter(tabFoodList);
        binding.rcvTabFood.setAdapter(adapter);

        MyFragmentManager.addFragment(getContext(), R.id.tabFoodContainer, new HomeAllFragment(), "homeAllFragment");
    }

    private void event() {

        adapter.setOnSelectTab(position -> {
            isLast = false;
            switch (position) {
                case 0:
                    HomeAllFragment homeAllFragment = new HomeAllFragment();
                    TabFoodTransaction(position, homeAllFragment, "homeAllFragment");
                    break;
                case 1:
                    HomeFoodFragment foodFragment = new HomeFoodFragment();
                    TabFoodTransaction(position, foodFragment, "foodFragment");
                    break;
                case 2:
                    HomeDrinkFragment drinkFragment = new HomeDrinkFragment();
                    TabFoodTransaction(position, drinkFragment, "drinkFragment");
                    break;
                case 3:
                    HomePizzaFragment pizzaFragment = new HomePizzaFragment();
                    TabFoodTransaction(position, pizzaFragment, "pizzaFragment");
                    break;
                case 4:
                    HomeChickenFragment chickenFragment = new HomeChickenFragment();
                    TabFoodTransaction(position, chickenFragment, "chickenFragment");
                    break;
                case 5:
                    HomeHotPotFragment hotPotFragment = new HomeHotPotFragment();
                    TabFoodTransaction(position, hotPotFragment, "hotPotFragment");
                    break;
            }
        });

        binding.nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) ->

        {
//            Log.d("bbb", "event: " + scrollY + " - " + (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()));

            if (scrollY >= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())
                    && scrollY > oldScrollY && !isLast) {
                isLast = true;
                FragmentManager fragmentManager = Objects.requireNonNull(MyFragmentManager.getFragmentManager(getContext()).findFragmentById(R.id.homeContainer)).getParentFragmentManager();
                switch (tabSelected) {
                    case 0:
                        HomeAllFragment allFragment = (HomeAllFragment) fragmentManager.findFragmentByTag("homeAllFragment");
                        assert allFragment != null;
                        allFragment.loadmore();
                        allFragment.setOnLoading(isLast1 -> isLast = isLast1);
                        break;
                    case 1:
                        HomeFoodFragment foodFragment = (HomeFoodFragment) fragmentManager.findFragmentByTag("foodFragment");
                        assert foodFragment != null;
                        foodFragment.loadmore();
                        foodFragment.setOnLoading(isLast1 -> isLast = isLast1);
                        break;
                    case 2:
                        HomeDrinkFragment drinkFragment = (HomeDrinkFragment) fragmentManager.findFragmentByTag("drinkFragment");
                        assert drinkFragment != null;
                        drinkFragment.loadmore();
                        drinkFragment.setOnLoading(isLast1 -> isLast = isLast1);
                        break;
                    case 3:
                        HomePizzaFragment pizzaFragment = (HomePizzaFragment) fragmentManager.findFragmentByTag("pizzaFragment");
                        assert pizzaFragment != null;
                        pizzaFragment.loadmore();
                        pizzaFragment.setOnLoading(isLast1 -> isLast = isLast1);
                        break;
                    case 4:
                        HomeChickenFragment chickenFragment = (HomeChickenFragment) fragmentManager.findFragmentByTag("chickenFragment");
                        assert chickenFragment != null;
                        chickenFragment.loadmore();
                        chickenFragment.setOnLoading(isLast1 -> isLast = isLast1);
                        break;
                    case 5:
                        HomeHotPotFragment hotPotFragment = (HomeHotPotFragment) fragmentManager.findFragmentByTag("hotPotFragment");
                        assert hotPotFragment != null;
                        hotPotFragment.loadmore();
                        hotPotFragment.setOnLoading(isLast1 -> isLast = isLast1);
                        break;
                }
            }
        });

        binding.searchBar.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), SearchActivity.class));
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }


    private void TabFoodTransaction(int position, Fragment fragment, String tag) {
        if (position != tabSelected) {
            FragmentTransaction transaction = MyFragmentManager.getFragmentManager(getContext()).beginTransaction();
            if (position > tabSelected) {
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
            transaction.replace(R.id.tabFoodContainer, fragment, tag);
            transaction.commit();
            this.tabSelected = position;
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void observeData() {

        //get Banner
        JSONObject object = new JSONObject();
        try {
            object.put("function", "getBanner");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getHomeBanner(object.toString())
                .observe(getViewLifecycleOwner(), banners -> {
                    binding.sliderBanner.setSliderAdapter(new SliderHomeAdapter(getContext(), banners));
                    binding.sliderBanner.setIndicatorAnimation(IndicatorAnimationType.WORM);
                    binding.sliderBanner.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    binding.sliderBanner.startAutoCycle();
                    Glide.with(requireContext()).load(banners.get(4).getImage()).into(binding.imgBanner);
                });

        //get Collection
        object = new JSONObject();
        try {
            object.put("function", "getCollection");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getHomeCollection(object.toString())
                .observe(getViewLifecycleOwner(), collections -> {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rcvCollection.getContext(),
                            layoutManager.getOrientation());
                    dividerItemDecoration.setDrawable(requireActivity().getDrawable(R.drawable.space_between_item));
                    binding.rcvCollection.addItemDecoration(dividerItemDecoration);
                    binding.rcvCollection.setLayoutManager(layoutManager);
                    binding.rcvCollection.setAdapter(new RCV_HomeCollection_Adapter(collections));
                });

        //get Category
        object = new JSONObject();
        try {
            object.put("function", "getCategory");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getHomeCategory(object.toString())
                .observe(getViewLifecycleOwner(), categories -> {
                    binding.rcvCategory.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));
                    RCV_HomeCategory_Adapter adapter = new RCV_HomeCategory_Adapter(categories);
                    binding.rcvCategory.setAdapter(adapter);
                    adapter.setOnSelectCategory(category -> handler.onSendCate(category));
                });
    }


    public void setOnSendCate(onSendCate onSendCate) {
        this.handler = onSendCate;
    }

}