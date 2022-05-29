package com.example.mynote.interfaces;

import com.example.mynote.services.s.ToastHelper;

public interface BaseCallBack<T> {
    default void onDone(){
        ToastHelper.closeLoadingDialog();
    };
    void onSuccess(T response);
    default void onFailure(String message){
        ToastHelper.showToast(message);
    };
}
