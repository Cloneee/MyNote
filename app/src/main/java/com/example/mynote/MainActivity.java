package com.example.mynote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynote.api.App;
import com.example.mynote.api.Auth;
import com.example.mynote.configs.Constant;
import com.example.mynote.interfaces.CustomCallBack;
import com.example.mynote.models.User;
import com.example.mynote.models.UserType;
import com.example.mynote.repos.AuthenticationRepository;
import com.example.mynote.screens.home.HomeScreen;
import com.example.mynote.services.s.RunOnUIHelper;
import com.example.mynote.services.s.ToastHelper;
import com.example.mynote.screens.login.LoginScreen;
import com.example.mynote.services.storage.ShareReferenceHelper;

public class MainActivity extends AppCompatActivity {
    AuthenticationRepository repository = AuthenticationRepository.getInstance();

    static public void login(Context context, String token, User u){
        App.getApp().setToken(token);
        user.setType(u.getType());
        user.setVerified(u.isVerified());
        user.setEmail(u.getEmail());

        Log.e("TAG", "token " + token );
        ShareReferenceHelper.getInstance().storeString(Constant.API_TOKEN, token);
        ShareReferenceHelper.getInstance().storeString(Constant.USER_EMAIL, u.getEmail());
        // 1 is verified
        // 0 is not
        ShareReferenceHelper.getInstance().storeString(Constant.USER_EMAIL_VERIFY, u.isVerified() ? "1" : "0");

        String v = ShareReferenceHelper.getInstance().getString(Constant.USER_EMAIL_VERIFY);

        Log.e("TAG", "v: " +v.toString() );
        Intent intent = new Intent(context, HomeScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    static public void logout(Context context){
        App.getApp().setToken("");
        user.setType(UserType.GUEST);
        user.setVerified(false);
        user.setEmail("");

        ShareReferenceHelper.getInstance().storeString(Constant.API_TOKEN, "");
        ShareReferenceHelper.getInstance().storeString(Constant.USER_EMAIL, "");
        ShareReferenceHelper.getInstance().storeString(Constant.USER_EMAIL_VERIFY, "0");

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

        ShareReferenceHelper.getInstance().init(PreferenceManager.getDefaultSharedPreferences(this));

//        String token = ShareReferenceHelper.getInstance().getString(Constant.API_TOKEN);
//
//        String v = ShareReferenceHelper.getInstance().getString(Constant.USER_EMAIL_VERIFY);
//
//        Log.e("TAG", "v: " +v.toString() );
//
//        if(!token.isEmpty()){
//            repository.getModifiedDate(token, new CustomCallBack() {
//                @Override
//                public void run(Object res) {
//                    login(
//                        MainActivity.this,
//                        token,
//                        new User("", ShareReferenceHelper.getInstance().getString(Constant.USER_EMAIL), UserType.NORMAL, ShareReferenceHelper.getInstance().getString(Constant.USER_EMAIL_VERIFY).equals("1")
//                        )
//                    );
//                }
//
//                @Override
//                public void run2(Object res) {
//                    logout(MainActivity.this);
//                }
//            });
//        }else{
//            logout(this);
//        }
        logout(this);
    }
}