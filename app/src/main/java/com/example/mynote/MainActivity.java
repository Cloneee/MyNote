package com.example.mynote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynote.api.App;
import com.example.mynote.api.Auth;
import com.example.mynote.models.User;
import com.example.mynote.models.UserType;
import com.example.mynote.screens.home.HomeScreen;
import com.example.mynote.services.s.RunOnUIHelper;
import com.example.mynote.services.s.ToastHelper;
import com.example.mynote.screens.login.LoginScreen;

public class MainActivity extends AppCompatActivity {

    static public void login(Context context, String token, User u){
        App.getApp().setToken(token);
        user.setType(u.getType());
        user.setVerified(u.isVerified());

        Intent intent = new Intent(context, HomeScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    static public void logout(Context context){
        App.getApp().setToken("");
        user.setType(UserType.GUEST);
        user.setVerified(false);

        Intent intent = new Intent(context, LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_MyNote);
//        setContentView(R.layout.activity_main);
        RunOnUIHelper.init(this);
        ToastHelper.init(this);
        Auth.getAuth();
        logout(this);
    }
}