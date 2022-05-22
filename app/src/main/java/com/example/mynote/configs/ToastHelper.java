package com.example.mynote.configs;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ToastHelper {

    private static ToastHelper INSTANCE;
    private static Context context;

    static Toast toast;

    private ToastHelper() {
    }

    public static ToastHelper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ToastHelper();
        }
        return INSTANCE;
    }

    static public void init (Context context1){
        context = context1;
    }

    static public void showToast (String message) {
        try{
            if(context == null) return;
            if(toast != null) {
                toast.cancel();
                toast = null;
            }
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.show();
        }catch (Exception e){
            Log.e("TAG", e.toString());
        }
    }
}
