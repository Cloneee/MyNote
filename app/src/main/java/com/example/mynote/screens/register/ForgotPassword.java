package com.example.mynote.screens.register;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.R;
import com.example.mynote.configs.Constant;
import com.example.mynote.databinding.ActivityForgotPasswordBinding;
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.services.s.ToastHelper;

public class ForgotPassword extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    ActivityResultLauncher<Intent> mStartForResult;
    AuthenticationRepository repository = AuthenticationRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                try {
                    if(result == null){
                        ToastHelper.showToast("Verifying fail");
                        return;
                    }

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent i = new Intent(this, ChangePassword.class);
                        i.putExtra(Constant.PASSWORD_SCREEN_TYPE, 0);
                        i.putExtra(Constant.OTP_MAIL, binding.email.getText().toString());
                        i.putExtra(Constant.OTP_VALUE, result.getData().getStringExtra(Constant.OTP_VALUE));
                        startActivity(i);
                    }else{
                        ToastHelper.showToast("Verifying fail");
                    }
                }catch (Exception e){
                    Log.e("TAG", "error: " + e);
                }

            }
        );

        binding.sendOtp.setOnClickListener(view1 -> {
//            gen otp
            ToastHelper.showLoadingDialog(this);

            repository.genOtp(binding.email.getText().toString(), res -> {
                ToastHelper.showLoadingDialog(this);

                Intent i = new Intent(this, OtpScreen.class);
                //0 is verify email
                //1 is other otp
                i.putExtra(Constant.OTP_RESULT, 1);
                i.putExtra(Constant.OTP_MAIL, binding.email.getText().toString());
                mStartForResult.launch(i);
            });


        });
    }

}