package com.example.mynote.screens.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.R;
import com.example.mynote.configs.Constant;
import com.example.mynote.databinding.ActivityChangePasswordBinding;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;

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

    }

    void resetPassword(){

    }
}