package com.example.mynote.api;

import com.example.mynote.interfaces.LoginResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;

public class Note {
    private static String URL = "https://android-server-781.herokuapp.com";
    private static String API = "/api/note";
    private static Auth auth = Auth.getAuth();
    private String token = auth.getToken();
    private static final Note note = new Note();
    private static final Moshi moshi = new Moshi.Builder().build();
    private static final OkHttpClient client = new OkHttpClient();

    private static final JsonAdapter<LoginResponse> noteAdapter = moshi.adapter(LoginResponse.class);

    private Note(){}
    public static Note getNote(){
        return note;
    }
}
