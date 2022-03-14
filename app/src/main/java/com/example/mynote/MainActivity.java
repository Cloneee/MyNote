package com.example.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynote.databinding.ActivityMainBinding;
import com.example.mynote.screens.LoginScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, LoginScreen.class));
    }
}