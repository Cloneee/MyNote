package com.example.mynote.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mynote.R;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_login_screen);
    }
}