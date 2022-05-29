package com.example.mynote.repos;

import android.content.Context;

import com.example.mynote.api.App;
import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.interfaces.CustomCallBack;
import com.example.mynote.models.Note;
import com.example.mynote.services.s.ToastHelper;

import java.util.ArrayList;

public class AppRepository {
    private static AppRepository instance;

    private AppRepository(){}

    public static AppRepository getInstance(){
        if(instance == null)
            instance = new AppRepository();
        return instance;
    }

    App app = App.getApp();

    public void getNotes(Context context, CustomCallBack callBack){
        ToastHelper.showLoadingDialog(context);
        app.getNotes(new BaseCallBack<ArrayList<Note>>(){
            @Override
            public void onSuccess(ArrayList<Note> response) {
                callBack.run(response);
            }

            @Override
            public void onFailure(String message) {
                callBack.run2(null);
                BaseCallBack.super.onFailure(message);
            }
        });
    }

    public void createNote(Context context, Note note, CustomCallBack callBack){
        ToastHelper.showLoadingDialog(context);
        app.createNote(note, new BaseCallBack<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                callBack.run(response);
            }
        });
    }

    public void changeNote(Context context, Note note, CustomCallBack callBack){
        ToastHelper.showLoadingDialog(context);
        app.editNote(note.getId(), note, new BaseCallBack<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                callBack.run(response);
            }
        });
    }

    public void deleteNote(Context context, String id, CustomCallBack callBack){
        ToastHelper.showLoadingDialog(context);
        app.deleteNote(id, new BaseCallBack<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                callBack.run(response);
            }
        });
    }
}
