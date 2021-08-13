package com.example.now.View.UserView.Profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.now.Model.Object.User;
import com.example.now.R;
import com.example.now.Repository.UserRepository;
import com.example.now.View.AddressView.Activity.AddressActivity;
import com.example.now.ViewModel.UserViewModel;
import com.example.now.databinding.FragmentUserProfileBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

public class UserProfileFragment extends Fragment {

    private FragmentUserProfileBinding binding;
    private UserViewModel viewModel;
    private String token;
    private JSONObject object;
    private int REQUEST_CODE = 123;
    private BottomSheetDialog dialogSex, dialogChange;
    private User user;
    private int sex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false);

        mapView();
        observeData();
        event();

        return binding.getRoot();
    }

    private void mapView() {
        token = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE).getString("token", "");

        viewModel = new ViewModelProvider.Factory() {
            @NonNull
            @NotNull
            @Override
            public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
                return (T) new UserViewModel(new UserRepository());
            }
        }.create(UserViewModel.class);
    }

    private void observeData() {
        object = new JSONObject();
        try {
            object.put("function", "getUserProfile");
            object.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewModel.getUserProfile(object.toString())
                .observe(getViewLifecycleOwner(), user -> {
                    Log.d("bbb", "observeData: " + user.toString());
                    this.user = user;
                    Glide.with(requireContext()).load(user.getImage()).into(binding.avatar);
                    binding.name.setText(user.getName());
                    binding.phone.setText(user.getPhone());
                    binding.birth.setText(user.getDateofbirth());
                    binding.email.setText(user.getEmail());
                    switch (user.getSex()) {
                        case "1":
                            binding.sex.setText("Nam");
                            break;
                        case "0":
                            binding.sex.setText("Nữ");
                            break;
                        case "-1":
                            binding.sex.setText("Khác");
                            break;
                        default:
                            binding.sex.setText("---");
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void event() {
        binding.btnBack.setOnClickListener(v -> {
            requireActivity().finish();
            requireActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });

        binding.btnChangeimg.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            requireActivity().startActivityForResult(photoPickerIntent, REQUEST_CODE);
        });

        binding.sex.setOnClickListener(v -> {
            dialogSex = new BottomSheetDialog(requireContext());
            dialogSex.setContentView(R.layout.dialog_choose_sex);
            LinearLayout blockMale = dialogSex.findViewById(R.id.block_male);
            LinearLayout blockFemale = dialogSex.findViewById(R.id.block_female);
            LinearLayout blockOther = dialogSex.findViewById(R.id.block_other);

            if (blockMale != null) {
                blockMale.setOnClickListener(v1 -> {
                    binding.sex.setText("Nam");
                    sex = 1;
                    dialogSex.dismiss();
                });
            }

            if (blockFemale != null) {
                blockFemale.setOnClickListener(v1 -> {
                    binding.sex.setText("Nữ");
                    sex = -1;
                    dialogSex.dismiss();
                });
            }

            if (blockOther != null) {
                blockOther.setOnClickListener(v1 -> {
                    binding.sex.setText("Khác");
                    sex = 0;
                    dialogSex.dismiss();
                });
            }
            dialogSex.show();
        });

        binding.name.setOnClickListener(v -> {
            dialogChange = new BottomSheetDialog(requireContext());
            dialogChange.setContentView(R.layout.dialog_change_profile);
            TextView title = dialogChange.findViewById(R.id.title);
            TextInputEditText editText = dialogChange.findViewById(R.id.editText);
            Button btnChange = dialogChange.findViewById(R.id.btnChange);

            if (btnChange != null && editText != null && title != null) {
                title.setText("Họ tên");
                editText.setText(this.user.getName());

                btnChange.setOnClickListener(v1 -> {
                    binding.name.setText(Objects.requireNonNull(editText.getText()).toString());
                    dialogChange.dismiss();
                });
            }
            dialogChange.show();
        });

        binding.email.setOnClickListener(v -> {
            dialogChange = new BottomSheetDialog(requireContext());
            dialogChange.setContentView(R.layout.dialog_change_profile);
            TextView title = dialogChange.findViewById(R.id.title);
            TextInputEditText editText = dialogChange.findViewById(R.id.editText);
            Button btnChange = dialogChange.findViewById(R.id.btnChange);

            if (btnChange != null && editText != null && title != null) {
                title.setText("Email");
                editText.setText(this.user.getEmail());
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                btnChange.setOnClickListener(v1 -> {
                    binding.email.setText(Objects.requireNonNull(editText.getText()).toString());
                    dialogChange.dismiss();
                });
            }
            dialogChange.show();
        });

        binding.birth.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> binding.birth.setText(datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear()), mYear, mMonth, mDay);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            dialog.setTitle("Ngày sinh");
            dialog.show();
        });

        binding.btnSave.setOnClickListener(v -> {
            object = new JSONObject();
            try {
                object.put("function", "editUser");
                object.put("token", token);
                object.put("name", binding.name.getText().toString());
                object.put("sex", sex);
                object.put("birth", binding.birth.getText().toString());
                object.put("email", binding.email.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            viewModel.editUser(object.toString())
                    .observe(getViewLifecycleOwner(), responseData -> {
                        if (responseData.getResult().equals("1")) {
                            Toast.makeText(getContext(), "Đã lưu thông tin!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Lưu thông tin thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        binding.changePassword.setOnClickListener(v -> {
            ((AddressActivity)requireActivity()).onClickChangePassword();
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        Log.d("bbb", "onActivityResult: " + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                Uri selectedImage = data != null ? data.getData() : null;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImage);
                    binding.avatar.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }
            }
        }
    }
}