package com.example.mynote.repos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import com.example.mynote.api.Auth;
import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.interfaces.CustomCallBack;
import com.example.mynote.models.LoginParams;
import com.example.mynote.models.LoginResponse;
import com.example.mynote.models.RegisterParams;
import com.example.mynote.screens.home.HomeScreen;
import com.example.mynote.services.s.ToastHelper;

public class AuthenticationRepository {
    Auth auth = Auth.getAuth();

    public void login(String username, String password, CustomCallBack callBack){

        auth.login(
                new LoginParams(username, password),
                new BaseCallBack<LoginResponse>(){
                    @Override
                    public void onSuccess(LoginResponse response) {
                        if(response == null) {
                            ToastHelper.showToast("Cannot get response");
                            return;
                        }
                        if(response.token.isEmpty()|| response.username.isEmpty()){
                            ToastHelper.showToast("User name or password i s incorrect");
                            return;
                        }
                        callBack.run(response.token);
                    }

                    @Override
                    public void onFailure(String meg) {
                        ToastHelper.showToast(meg);
                    }

                    @Override
                    public void onDone() {
                        ToastHelper.closeLoadingDialog();
                    }
                }
        );
    }

    public void register(String username, String password, String email, CustomCallBack callBack){
        auth.register(
                new RegisterParams(username, password, email),
                new BaseCallBack<Boolean>(){
                    @Override
                    public void onDone() {
                    }

                    @Override
                    public void onSuccess(Boolean response) {
                        if(response)
                            callBack.run(null);
                        else
                            ToastHelper.showToast("Check your input again");
                    }

                    @Override
                    public void onFailure(String message) {
                        ToastHelper.showToast(message);
                        ToastHelper.closeLoadingDialog();
                    }
                }
        );
    }
}
