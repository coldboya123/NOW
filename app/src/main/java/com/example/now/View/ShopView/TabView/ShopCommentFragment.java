package com.example.now.View.ShopView.TabView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.now.Model.Object.Comment;
import com.example.now.Model.Object.Shop;
import com.example.now.R;
import com.example.now.Repository.ShopRepository;
import com.example.now.View.ShopView.RCV_Comment_Adapter;
import com.example.now.ViewModel.ShopViewModel;
import com.example.now.databinding.FragmentShopCommentBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ShopCommentFragment extends Fragment {

    private FragmentShopCommentBinding binding;
    private ShopViewModel viewModel;
    private JSONObject object;
    private Shop shop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShopCommentBinding.inflate(inflater, container, false);

        mapview();
        observeData();

        return binding.getRoot();
    }

    private void mapview() {
        shop = (Shop) requireActivity().getIntent().getSerializableExtra("shop");
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new ShopViewModel(new ShopRepository());
            }
        }.create(ShopViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    private void observeData(){
        object = new JSONObject();
        try {
            object.put("function", "getShopComment");
            object.put("id", shop.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getShopComment(object.toString())
                .observe(getViewLifecycleOwner(), comments -> {
                    if (comments.size() > 0){
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        binding.recyclerView.setAdapter(new RCV_Comment_Adapter(comments));
                        binding.averageRate.setText(getTotalRate(comments) + "");
                        binding.totalRating.setText(comments.size() + " đánh giá");
                        setProgress(binding.progress5, binding.num5, comments, 5);
                        setProgress(binding.progress4, binding.num4, comments, 4);
                        setProgress(binding.progress3, binding.num3, comments, 3);
                        setProgress(binding.progress2, binding.num2, comments, 2);
                        setProgress(binding.progress1, binding.num1, comments, 1);
                        binding.avarageRating.setRating(getTotalRate(comments));
                        binding.blockNoComment.setVisibility(View.GONE);
                    } else {
                        binding.blockRating.setVisibility(View.GONE);
                        binding.blockNoComment.setVisibility(View.VISIBLE);
                    }
                });
    }

    private float getTotalRate(List<Comment> list){
        float totalRate = 0;
        for (int i = 0; i < list.size(); i++) {
            totalRate += Float.parseFloat(list.get(i).getRate());
        }
        return totalRate/list.size();
    }

    private void setProgress(ProgressBar progress, TextView textView, List<Comment> list, int rate){
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getRate()) == rate){
                count ++;
            }
        }
        progress.setProgress(count*100/list.size());
        textView.setText(count + "");
    }
}