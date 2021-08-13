package com.example.now.View.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.now.Repository.SearchRepository;
import com.example.now.View.Adapter.RCV_Shop_Adapter;
import com.example.now.ViewModel.SearchViewModel;
import com.example.now.databinding.ActivitySearchBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private SearchViewModel viewModel;
    private JSONObject object;
    private final Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mapView();
        event();
    }

    private void mapView() {
        LinearLayout linearLayout1 = (LinearLayout) binding.searchView.getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3.getChildAt(0);
        autoComplete.setTextSize(15);
        binding.searchView.setOnClickListener(v1 -> {
            binding.searchView.setIconified(false);
        });

        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new SearchViewModel(new SearchRepository());
            }
        }.create(SearchViewModel.class);

        object = new JSONObject();
        try {
            object.put("function", "searchShop");
            object.put("queue", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.searchShop(object.toString())
                .observe(SearchActivity.this, shops -> {
                    binding.image.setVisibility(shops.size()>0 ? View.GONE : View.VISIBLE);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false));
                    binding.recyclerView.setAdapter(new RCV_Shop_Adapter(shops));
                });
    }

    private void event() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacks(runnable);

                runnable = () -> {
                    object = new JSONObject();
                    try {
                        object.put("function", "searchShop");
                        object.put("queue", newText);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    viewModel.searchShop(object.toString())
                            .observe(SearchActivity.this, shops -> {
                                binding.image.setVisibility(shops.size()>0 ? View.GONE : View.VISIBLE);
                                binding.recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false));
                                binding.recyclerView.setAdapter(new RCV_Shop_Adapter(shops));
                            });
                };
                handler.postDelayed(runnable, 1000);

                return false;
            }
        });

        binding.btnBack.setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}