package com.example.mynote.api;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynote.interfaces.AuthCallBack;
import com.example.mynote.interfaces.LoginParams;
import com.example.mynote.interfaces.LoginResponse;
import com.example.mynote.interfaces.Register;
import com.example.mynote.services.s.RunOnUIHelper;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Auth {
    private static String URL = "https://android-server-781.herokuapp.com";
    private static String API = "/api/v1/user";
    private String token = "";
    private static Auth auth;
    private static final Moshi moshi = new Moshi.Builder().build();
    private static final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final JsonAdapter<LoginResponse> loginResponseAdapter = moshi.adapter(LoginResponse.class);
    private static final JsonAdapter<LoginParams> loginAdapter = moshi.adapter(LoginParams.class);
    private static final JsonAdapter<Register> registerAdapter = moshi.adapter(Register.class);

    private Auth(){}

    public static Auth getAuth(){
        if(auth == null){
            auth = new Auth();
        }
        return auth;
    }

    public AuthCallBack authCallBack;

    public void login(LoginParams login){
        String json = loginAdapter.toJson(login);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/login")
                .post(body)
                .build();

        RunOnUIHelper instance = RunOnUIHelper.getInstance();

        try{
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    instance.run(() -> {
                        authCallBack.onFailure(e.getMessage());
                        authCallBack.onDone();
                    });
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    instance.run(() -> {
                        LoginResponse res = null;
                        try {
                            Log.e("TAG", "onResponse: " + response.toString());
                            res = new LoginResponse(loginResponseAdapter.fromJson(response.body().source()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        authCallBack.onLoginSuccess(res);
                        authCallBack.onDone();
                    });
                }
            });

        } catch (Exception e) {
//            result = new LoginResponse();
            e.printStackTrace();
        }
    }

    public Boolean register(Register register){
        String json = registerAdapter.toJson(register);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/register")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getToken(){
        return token;
    }
}
