package com.example.now.View.AddressView.AddAddress.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.now.Model.Object.City;
import com.example.now.Model.Object.District;
import com.example.now.Model.Object.MyFragmentManager;
import com.example.now.Model.Object.Ward;
import com.example.now.R;
import com.example.now.Repository.AddressRepository;
import com.example.now.View.AddressView.AddAddress.adapter.ChooseCity.RCV_AddressChooseCity_Adapter;
import com.example.now.View.AddressView.AddAddress.adapter.ChooseDistrict.RCV_AddressChooseDistrict_Adapter;
import com.example.now.View.AddressView.AddAddress.adapter.ChooseWard.RCV_AddressChooseWard_Adapter;
import com.example.now.ViewModel.AddressViewModel;
import com.example.now.databinding.FragmentAddAddressBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddAddressFragment extends Fragment {

    private FragmentAddAddressBinding binding;
    private AddressViewModel viewModel;
    private String token;
    private final int HOUSE = 0, COMPANY = 1;
    private int type = -1;
    private AlertDialog dialog;
    private JSONObject object;
    private String address = "";

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddAddressBinding.inflate(inflater, container, false);

        mapView();
        event();

        return binding.getRoot();
    }

    private void mapView() {
        token = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");
        binding.txtCity.setRawInputType(InputType.TYPE_NULL);
        binding.txtCity.setFocusable(true);
        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new AddressViewModel(new AddressRepository());
            }
        }.create(AddressViewModel.class);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void event() {

        binding.txtCity.setOnClickListener(v -> {
            viewModel.getCity()
                    .observe(getViewLifecycleOwner(), cityResponse -> {
                        Log.d("bbb", "event: " + cityResponse.toString());
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
                        View layoutView = getLayoutInflater().inflate(R.layout.dialog_choose_address, null);
                        dialogBuilder.setView(layoutView);
                        dialog = dialogBuilder.create();
                        SearchView searchView = layoutView.findViewById(R.id.search);
                        RecyclerView recyclerView = layoutView.findViewById(R.id.rcv);
                        TextView titleCity = layoutView.findViewById(R.id.city);
                        TextView titleDistrict = layoutView.findViewById(R.id.district);
                        TextView titleWard = layoutView.findViewById(R.id.ward);

                        LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
                        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
                        LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
                        AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3.getChildAt(0);
                        autoComplete.setTextSize(15);
                        searchView.setOnClickListener(v1 -> {
                            searchView.setIconified(false);
                        });
                        List<City> listCity = cityResponse.getCityList();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        RCV_AddressChooseCity_Adapter adapter = new RCV_AddressChooseCity_Adapter(listCity);
                        recyclerView.setAdapter(adapter);
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                newText = newText.toLowerCase();
                                List<City> newCityList = new ArrayList<>();
                                for (City city : listCity) {
                                    String checkcity = city.getProvinceName().toLowerCase();
                                    if (checkcity.contains(newText)) {
                                        newCityList.add(city);
                                    }
                                }
                                adapter.setFilter(newCityList);
                                return true;
                            }
                        });

                        adapter.setOnSelectCityListener(city -> {
                            address = "";
                            address += city.getProvinceName();
                            titleCity.setText(city.getProvinceName() + " > ");
                            titleDistrict.setVisibility(View.VISIBLE);
                            viewModel.getDistrict(city.getProvinceId())
                                    .observe(getViewLifecycleOwner(), districtResponse -> {
                                        List<District> listDistrict = districtResponse.getDistrictList();
                                        RCV_AddressChooseDistrict_Adapter districtAdapter = new RCV_AddressChooseDistrict_Adapter(listDistrict);
                                        recyclerView.setAdapter(districtAdapter);
                                        searchView.setQuery("", false);
                                        searchView.clearFocus();
                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String query) {
                                                return false;
                                            }

                                            @Override
                                            public boolean onQueryTextChange(String newText) {
                                                newText = newText.toLowerCase();
                                                List<District> newDistrictList = new ArrayList<>();
                                                for (District district : listDistrict) {
                                                    String checkDistrict = district.getDistrictName().toLowerCase();
                                                    if (checkDistrict.contains(newText)) {
                                                        newDistrictList.add(district);
                                                    }
                                                }
                                                districtAdapter.setFilter(newDistrictList);
                                                return true;
                                            }
                                        });

                                        districtAdapter.setOnSelectDistrictListener(district -> {
                                            address = district.getDistrictName() + ", " + address;
                                            titleDistrict.setText(district.getDistrictName() + " > ");
                                            titleWard.setVisibility(View.VISIBLE);
                                            viewModel.getWard(district.getDistrictId())
                                                    .observe(getViewLifecycleOwner(), wardResponse -> {
                                                        List<Ward> listWard = wardResponse.getWardList();
                                                        RCV_AddressChooseWard_Adapter wardAdapter = new RCV_AddressChooseWard_Adapter(listWard);
                                                        recyclerView.setAdapter(wardAdapter);
                                                        searchView.setQuery("", false);
                                                        searchView.clearFocus();
                                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                                            @Override
                                                            public boolean onQueryTextSubmit(String query) {
                                                                return false;
                                                            }

                                                            @Override
                                                            public boolean onQueryTextChange(String newText) {
                                                                newText = newText.toLowerCase();
                                                                List<Ward> newWardList = new ArrayList<>();
                                                                for (Ward ward : listWard) {
                                                                    String checkWard = ward.getWardName().toLowerCase();
                                                                    if (checkWard.contains(newText)) {
                                                                        newWardList.add(ward);
                                                                    }
                                                                }
                                                                wardAdapter.setFilter(newWardList);
                                                                return true;
                                                            }
                                                        });
                                                        wardAdapter.setOnSelectWardListener(ward -> {
                                                            address = ward.getWardName() + ", " + address;
                                                            titleDistrict.setText(ward.getWardName());
                                                            dialog.dismiss();
                                                            binding.txtCity.setText(address, false);
                                                        });
                                                    });
                                        });
                                    });
                        });
                        dialog.show();
                    });
        });

        binding.typeHouse.setOnClickListener(v -> {
            binding.typeHouse.setBackground(requireActivity().getDrawable(R.drawable.background_btnlogout));
            binding.typeHouse.setTextColor(requireActivity().getColor(R.color.primary_color));
            binding.typeCompany.setBackground(requireActivity().getDrawable(R.drawable.outline_background));
            binding.typeCompany.setTextColor(requireActivity().getColor(R.color.gray));
            type = HOUSE;
        });

        binding.typeCompany.setOnClickListener(v -> {
            binding.typeCompany.setBackground(requireActivity().getDrawable(R.drawable.background_btnlogout));
            binding.typeCompany.setTextColor(requireActivity().getColor(R.color.primary_color));
            binding.typeHouse.setBackground(requireActivity().getDrawable(R.drawable.outline_background));
            binding.typeHouse.setTextColor(requireActivity().getColor(R.color.gray));
            type = COMPANY;
        });

        binding.btnAdd.setOnClickListener(v -> {
            if (!validateField()) {
                Toast.makeText(getContext(), "Không được để trống!", Toast.LENGTH_SHORT).show();
            } else {
                observeData();
            }
        });

        binding.btnBack.setOnClickListener(v -> MyFragmentManager.getFragmentManager(getContext()).popBackStack());
    }

    private void observeData() {
        JSONObject object = new JSONObject();
        try {
            object.put("function", "addUserAddress");
            object.put("token", token);
            object.put("address", binding.txtAddress.getText().toString() + ", " + binding.txtCity.getText().toString());
            object.put("title", type==0 ? "Nhà" : "Công ty");
            object.put("name", binding.txtName.getText().toString());
            object.put("phone", binding.txtPhone.getText().toString());
            object.put("note", binding.txtNote.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewModel.addUserAddress(object.toString())
                .observe(getViewLifecycleOwner(), responseData -> {
                    if (responseData.getResult().equals("1")) {
                        MyFragmentManager.getFragmentManager(getContext()).popBackStack();
                    } else {
                        Toast.makeText(getContext(), "Thêm địa chỉ không thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validateField() {
        return !binding.txtCity.getText().toString().isEmpty()&&!binding.txtAddress.getText().toString().isEmpty() && !binding.txtName.getText().toString().isEmpty()
                && !binding.txtPhone.getText().toString().isEmpty() && type != -1;
    }
}