package com.example.mynote.repos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mynote.api.Auth;
import com.example.mynote.configs.Constant;
import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.interfaces.CustomCallBack;
import com.example.mynote.models.LoginParams;
import com.example.mynote.models.LoginResponse;
import com.example.mynote.models.RegisterParams;
import com.example.mynote.models.VerifyOtpParams;
import com.example.mynote.screens.home.HomeScreen;
import com.example.mynote.services.s.ToastHelper;
import com.example.mynote.services.storage.ShareReferenceHelper;

public class AuthenticationRepository {
    private static AuthenticationRepository instance;

    private AuthenticationRepository(){}

    public static AuthenticationRepository getInstance(){
        if(instance == null)
            instance = new AuthenticationRepository();
        return instance;
    }

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
                        callBack.run(response);
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
                    else {
                        ToastHelper.showToast("Check your input again");
                        ToastHelper.closeLoadingDialog();
                    }
                }

                @Override
                public void onFailure(String message) {
                    ToastHelper.showToast(message);
                    ToastHelper.closeLoadingDialog();
                }
            }
        );
    }

    public void genOtp(String email, CustomCallBack callBack){
        auth.genOtp(email, response -> {
            if((boolean) !response){
                ToastHelper.showToast("Cannot send this request, try again latter");
                return;
            }
            callBack.run(null);
        });
    }

    public void verifyOtp(String email, String otp, CustomCallBack callBack){
        auth.verifyOtp(new VerifyOtpParams(email, otp), response -> {
            if((boolean) response){
                callBack.run(null);
            }else {
                ToastHelper.showToast("Cannot send this request, try again latter");
            }
        });
    }

    public void verifyEmail(String email, String otp, CustomCallBack callBack){
        auth.verifyEmail(new VerifyOtpParams(email, otp), response -> {
            if((boolean) response){
                ShareReferenceHelper.getInstance().storeString(Constant.USER_EMAIL_VERIFY, "1");
                callBack.run(null);
            }else {
                ToastHelper.showToast("Cannot send this request, try again latter");
            }
        });
    }

    public void changePassword(String password, String newPassword, String token, CustomCallBack callBack){
        auth.changePassword(password, newPassword, token, response -> {
            if((boolean) response){
                callBack.run(null);
            }else {
                ToastHelper.showToast("Cannot send this request, try again latter");
            }
        });
    }

    public void recoverPassword(String email, String otp, String newPassword, CustomCallBack callBack){
        auth.recoverPassword(email, otp, newPassword, response -> {
            if((boolean) response){
                callBack.run(null);
            }else {
                ToastHelper.showToast("Cannot send this request, try again latter");
            }
        });
    }

    public void getModifiedDate(String token, CustomCallBack callBack){
        auth.getModifiedDate(token, response -> {
            Log.e("TAG", "getModifiedDate: " + response );
            if((boolean) response){
                callBack.run(null);
            }else {
                callBack.run2(null);
                ToastHelper.showToast("Cannot send this request, try again latter");
            }
        });
    }
}
