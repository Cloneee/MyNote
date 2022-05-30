package com.example.mynote.screens.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.MainActivity;
import com.example.mynote.R;
import com.example.mynote.api.Auth;
import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.databinding.ActivityRegisterBinding;
import com.example.mynote.models.LoginParams;
import com.example.mynote.models.LoginResponse;
import com.example.mynote.models.RegisterParams;
import com.example.mynote.models.User;
import com.example.mynote.models.UserType;
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.screens.home.HomeScreen;
import com.example.mynote.services.s.ToastHelper;

public class RegisterScreen extends AppCompatActivity {
    ActivityRegisterBinding binding;

    AuthenticationRepository authenticationRepos = AuthenticationRepository.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginButton.setOnClickListener(view1 -> finish());
        binding.registerButton.setOnClickListener(view1 -> register());
    }

    void register (){
        if(binding.username.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(binding.password.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(binding.confirmPassword.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(binding.email.getText().toString().isEmpty()){
            ToastHelper.showToast("User name cannot be empty");
            return;
        }
        if(!binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())){
            ToastHelper.showToast("Confirm password is not match with password");
            return;
        }

        ToastHelper.showLoadingDialog(this);
        authenticationRepos.register(binding.username.getText().toString(), binding.password.getText().toString(), binding.email.getText().toString(),
            (res) -> {
                login();
            }
        );
    }

    void login (){
        authenticationRepos.login(binding.username.getText().toString(), binding.password.getText().toString(),
            (res)->{
                MainActivity.login(this, ((LoginResponse) res).token, new User("", UserType.NORMAL, ((LoginResponse) res).verify));
            }
        );
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}