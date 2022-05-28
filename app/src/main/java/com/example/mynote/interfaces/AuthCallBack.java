package com.example.mynote.interfaces;

public interface AuthCallBack {
    void onLoginSuccess(LoginResponse response);

    void onFailure(String message);

    void onDone();
}
