package com.example.mynote.screens.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.R;

import com.example.mynote.databinding.ActivityLoginScreenBinding;
import com.example.mynote.screens.register.RegisterScreen;
import com.example.mynote.screens.home.HomeScreen;

public class LoginScreen extends AppCompatActivity {
    private ActivityLoginScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_login_screen);

        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginButton.setOnClickListener(view1 -> startActivity(new Intent(getApplicationContext(), HomeScreen.class)));
        binding.guestLoginButton.setOnClickListener(view1 -> {
            Log.e("TAG", "guest");
        });
        binding.registerButton.setOnClickListener(view1 -> startActivity(new Intent(this, RegisterScreen.class)));
    }
}