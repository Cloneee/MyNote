package com.example.mynote.screens.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.R;

import com.example.mynote.api.Auth;
import com.example.mynote.services.s.ToastHelper;
import com.example.mynote.databinding.ActivityLoginScreenBinding;
import com.example.mynote.interfaces.AuthCallBack;
import com.example.mynote.interfaces.LoginParams;
import com.example.mynote.interfaces.LoginResponse;
import com.example.mynote.screens.register.RegisterScreen;
import com.example.mynote.screens.home.HomeScreen;

public class LoginScreen extends AppCompatActivity {
    private ActivityLoginScreenBinding binding;
    Auth authenticationRepos = Auth.getAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_login_screen);

        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.loginButton.setOnClickListener(view1 -> {
            ToastHelper.showLoadingDialog(this);
            try {
                authenticationRepos.login(new LoginParams(binding.username.getText().toString(), binding.password.getText().toString()));
                authenticationRepos.authCallBack = new AuthCallBack() {
                    @Override
                    public void onLoginSuccess(LoginResponse response) {
                        if(response == null) {
                            ToastHelper.showToast("Cannot get response");
                            return;
                        }

                        if(response.token.isEmpty()|| response.username.isEmpty()){
                            ToastHelper.showToast("User name or password i s incorrect");
                            return;
                        }

                        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                    }

                    @Override
                    public void onFailure(String meg) {
                        ToastHelper.showToast(meg);
                    }

                    @Override
                    public void onDone() {
                        ToastHelper.closeLoadingDialog();
                    }
                };


            }catch (Exception e){
                ToastHelper.showToast("Cannot get response");
                Log.e("TAG", e.toString() );
            }
        });
        binding.registerButton.setOnClickListener(view1 -> startActivity(new Intent(this, RegisterScreen.class)));

        binding.guestLoginButton.setOnClickListener(view1 -> {
            Log.e("TAG", "guest");
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