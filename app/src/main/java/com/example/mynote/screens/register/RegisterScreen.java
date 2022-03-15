package com.example.mynote.screens.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mynote.R;
import com.example.mynote.databinding.ActivityRegisterBinding;

public class RegisterScreen extends AppCompatActivity {
    ActivityRegisterBinding binding;

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
        Log.e("TAG", "register: " );
    }
}