package com.example.mynote.screens.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.MainActivity;
import com.example.mynote.R;

import com.example.mynote.models.User;
import com.example.mynote.models.UserType;
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.screens.register.OtpScreen;
import com.example.mynote.services.s.ToastHelper;
import com.example.mynote.databinding.ActivityLoginScreenBinding;
import com.example.mynote.screens.register.RegisterScreen;

public class LoginScreen extends AppCompatActivity {
    private ActivityLoginScreenBinding binding;
    AuthenticationRepository authenticationRepos = new AuthenticationRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_login_screen);

        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginButton.setOnClickListener(view1 -> {
            if(binding.username.getText().toString().isEmpty()){
                ToastHelper.showToast("User name cannot be empty");
                return;
            }
            if(binding.password.getText().toString().isEmpty()){
                ToastHelper.showToast("User name cannot be empty");
                return;
            }
            ToastHelper.showLoadingDialog(this);

            authenticationRepos.login(binding.username.getText().toString(), binding.password.getText().toString(),
                res -> {
                    MainActivity.login(this, res.toString(), new User(""));
                }
            );

        });
        binding.registerButton.setOnClickListener(view1 -> startActivity(new Intent(this, OtpScreen.class)));

        binding.guestLoginButton.setOnClickListener(view1 -> {
            MainActivity.login(this, "", new User());
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}