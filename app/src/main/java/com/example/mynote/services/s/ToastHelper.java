package com.example.mynote.services.s;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mynote.R;

public class ToastHelper {

    private static ToastHelper INSTANCE;
    private static Context context;

    static Toast toast;
    static Dialog dialog;

    private ToastHelper() {
    }

    public static ToastHelper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ToastHelper();
        }
        return INSTANCE;
    }

    static Context dialogContext;

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

    static public void showLoadingDialog(Context context){
        try {
            if(context == null) return;

            if(dialog != null)
                if(dialog.isShowing()) {
                    dialog.dismiss();
                }

            if(!context.equals(dialogContext)){
                dialog = new Dialog(context, android.R.style.Theme_Black);
                View views = LayoutInflater.from(context).inflate(R.layout.loading_layout, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawableResource(R.color.feed_item_bg);
                dialog.setContentView(views);
            }

            dialog.show();

            dialogContext = context;


        }catch (Exception e){
            Log.e("TAG", "showLoadingDialog: " + e.getMessage() );
        }
    }

    static public void closeLoadingDialog(){
        try {
            if(context == null) return;
            if(dialog.isShowing()) {
                dialog.dismiss();
            }
        }catch (Exception e){}
    }
}
