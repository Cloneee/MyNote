package com.example.mynote.screens.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.mynote.configs.Constant;
import com.example.mynote.databinding.ActivityOtpScreenBinding;
import com.example.mynote.models.Note;
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.services.s.RunOnUIHelper;
import com.example.mynote.services.s.ToastHelper;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mynote.R;

import java.util.Timer;
import java.util.TimerTask;

public class OtpScreen extends AppCompatActivity {
    AuthenticationRepository authenticationRepository = AuthenticationRepository.getInstance();

    RunOnUIHelper runOnUIHelper = RunOnUIHelper.getInstance();
    int expireTime = 3;
    String otp = "";
    String email = "";

    private AppBarConfiguration appBarConfiguration;
    private ActivityOtpScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    binding = ActivityOtpScreenBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

        Intent intent = getIntent();
        try {
            initData(intent);
        }catch (Exception e){
            Log.e("TAG", e.toString() );
        }

    binding.etOtp.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            otp = charSequence.toString();
            if(charSequence.toString().length() == 6 ){
                verify();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

    binding.verifyOtpButton.setOnClickListener(view -> {
        verify();
    });

    binding.resendButton.setOnClickListener(view -> {
        ToastHelper.showToast("The OTP has been sent");
    });

    new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                runOnUIHelper.run(() -> {
                    if(expireTime + 1 == 0){
                        return;
                    }
                    binding.expireTimeMinute.setText(Integer.toString((int)(expireTime/60)));
                    binding.expireTimeSecond.setText(Integer.toString(expireTime%60));
                    expireTime--;
                });
            }
        },0,1000);
    }

    void initData(Intent intent){
        if(intent == null){
            finish();
        }

        email = intent.getStringExtra(Constant.OTP_MAIL);

        int a = intent.getIntExtra(Constant.OTP_RESULT, 1);

        if(a == 0){
            binding.skipButton.setVisibility(View.VISIBLE);
            binding.skipButton.setOnClickListener(view -> {
                goBack();
            });
        }
    }

    void verify(){
        goBack();
//        authenticationRepository.verifyOtp(email, otp, res -> {
//            goBack();
//        });


    }

    void goBack(){
        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}