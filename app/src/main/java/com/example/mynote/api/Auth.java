package com.example.mynote.api;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.models.LoginParams;
import com.example.mynote.models.LoginResponse;
import com.example.mynote.models.RegisterParams;
import com.example.mynote.services.s.RunOnUIHelper;
import com.squareup.moshi.JsonAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Auth extends Base {
    private static String API = "/user";
    private static Auth auth;
    private static final JsonAdapter<LoginResponse> loginResponseAdapter = moshi.adapter(LoginResponse.class);
    private static final JsonAdapter<LoginParams> loginAdapter = moshi.adapter(LoginParams.class);
    private static final JsonAdapter<RegisterParams> registerAdapter = moshi.adapter(RegisterParams.class);

    private RunOnUIHelper instance = RunOnUIHelper.getInstance();

    private Auth(){}

    public static Auth getAuth(){
        if(auth == null){
            auth = new Auth();
        }
        return auth;
    }

    public void login(LoginParams login, BaseCallBack callBack){
        String json = loginAdapter.toJson(login);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/login")
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    instance.run(() -> {
                        callBack.onFailure(e.getMessage());
                        callBack.onDone();
                    });
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    instance.run(() -> {
                        Log.e("TAG", "onResponse: " + response);
                        LoginResponse res = new LoginResponse(loginResponseAdapter.fromJson(response.body().source()));

                        callBack.onSuccess(res);
                        callBack.onDone();
                    });
                }
            });


    }

    public void register(RegisterParams registerParams, BaseCallBack callBack){
        String json = registerAdapter.toJson(registerParams);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/register")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                instance.run(() -> {
                    callBack.onFailure(e.getMessage());
                    callBack.onDone();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                instance.run(() -> {
                    int code = response.code();
                    Log.e("TAG", "onResponse: " + response);
                    if(code == 200)
                        callBack.onSuccess(true);
                    else
                        callBack.onSuccess(false);
                    callBack.onDone();
                });
            }
        });

    }

}
