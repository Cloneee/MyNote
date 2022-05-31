package com.example.mynote.screens.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.MainActivity;
import com.example.mynote.R;
import com.example.mynote.configs.Constant;
import com.example.mynote.databinding.ActivityChangePasswordBinding;
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.services.s.ToastHelper;
import com.example.mynote.services.storage.ShareReferenceHelper;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;

    AuthenticationRepository repository = AuthenticationRepository.getInstance();

    String email = "";
    String otp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        try {
            initData(intent);
        }catch (Exception e){
            Log.e("TAG", e.toString() );
        }


    }

    void initData(Intent intent){
        if(intent == null){
            finish();
        }

        email = intent.getStringExtra(Constant.OTP_MAIL);

        otp = intent.getStringExtra(Constant.OTP_VALUE);

        int a = intent.getIntExtra(Constant.PASSWORD_SCREEN_TYPE, 1);

        //0 is recover password
        //1 is change password
        if(a == 0){
            binding.oldPassword.setVisibility(View.GONE);
            binding.saveButton.setOnClickListener(view -> {
                resetPassword();
            });
        }else {
            binding.saveButton.setOnClickListener(view -> {
                changePassword();
            });
        }
    }

    void changePassword(){
        if(binding.password.getText().toString().isEmpty() || binding.oldPassword.getText().toString().isEmpty() || binding.confirmPassword.getText().toString().isEmpty()){
            ToastHelper.showToast("Input cannot be empty");
            return;
        }

        if(!binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())){
            ToastHelper.showToast("New password and confirm password must match");
            return;
        }
        ToastHelper.showLoadingDialog(this);
        repository.changePassword(binding.oldPassword.getText().toString(), binding.password.getText().toString(), ShareReferenceHelper.getInstance().getString(Constant.API_TOKEN), res -> {
            MainActivity.logout(this);
        });
    }

    void resetPassword(){
        if(binding.password.getText().toString().isEmpty() || binding.confirmPassword.getText().toString().isEmpty()){
            ToastHelper.showToast("Input cannot be empty");
            return;
        }

        if(!binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())){
            ToastHelper.showToast("New password and confirm password must match");
            return;
        }
        ToastHelper.showLoadingDialog(this);
        repository.recoverPassword(email, otp, binding.password.getText().toString(), res -> {
            MainActivity.logout(this);
        });
    }
}