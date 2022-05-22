package com.example.mynote;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynote.configs.ToastHelper;
import com.example.mynote.databinding.ActivityMainBinding;
import com.example.mynote.screens.login.LoginScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_MyNote);
//        setContentView(R.layout.activity_main);
        ToastHelper.init(getBaseContext());
        startActivity(new Intent(this, LoginScreen.class));
    }
}