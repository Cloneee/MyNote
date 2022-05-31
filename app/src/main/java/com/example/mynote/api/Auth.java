package com.example.mynote.api;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.models.LoginParams;
import com.example.mynote.models.LoginResponse;
import com.example.mynote.models.RegisterParams;
import com.example.mynote.models.VerifyOtpParams;
import com.example.mynote.services.s.RunOnUIHelper;
import com.squareup.moshi.JsonAdapter;

import org.json.JSONException;
import org.json.JSONObject;

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
    private static final JsonAdapter<VerifyOtpParams> verifyOtpParamsJsonAdapter = moshi.adapter(VerifyOtpParams.class);

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
        Log.e("TAG", "register: " + json );
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
                    if(code == 200)
                        callBack.onSuccess(true);
                    else
                        callBack.onSuccess(false);
                    callBack.onDone();
                });
            }
        });
    }

    public void genOtp(String email, BaseCallBack<Boolean> baseCallBack) {

        JSONObject json = new JSONObject();

        try {
            json.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/otp")
                .post(body)
                .build();
        Log.e("TAG", "onResponse: " + json );
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                instance.run(() -> {
                    baseCallBack.onFailure(e.getMessage());
                    baseCallBack.onDone();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                instance.run(() -> {
                    Log.e("TAG", "onResponse: " + response );
                    if(response.code() == 200){
                        baseCallBack.onSuccess(true);
                    }else
                        baseCallBack.onSuccess(false);
                    baseCallBack.onDone();
                });
            }
        });
    }

    public void verifyOtp(VerifyOtpParams params, BaseCallBack<Boolean> baseCallBack){

        String json = verifyOtpParamsJsonAdapter.toJson(params);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/otp")
                .put(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                instance.run(() -> {
                    baseCallBack.onFailure(e.getMessage());
                    baseCallBack.onDone();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                instance.run(() -> {
                    Log.e("TAG", "onResponse: " + response );
                    if(response.code() == 200){
                        baseCallBack.onSuccess(true);
                    }else
                        baseCallBack.onSuccess(false);
                    baseCallBack.onDone();
                });
            }
        });
    }

    public void verifyEmail(VerifyOtpParams params, BaseCallBack<Boolean> baseCallBack){
        String json = verifyOtpParamsJsonAdapter.toJson(params);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/email")
                .post(body)
                .build();

        Log.e("TAG", "verifyEmail: " + json );
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                instance.run(() -> {
                    baseCallBack.onFailure(e.getMessage());
                    baseCallBack.onDone();
                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                instance.run(() -> {
                    if(response.code() == 200){
                        baseCallBack.onSuccess(true);
                    }else
                        baseCallBack.onSuccess(false);
                    baseCallBack.onDone();
                });
            }
        });
    }

    public void changePassword(String password, String newPassword, String token, BaseCallBack<Boolean> baseCallBack){

        JSONObject json = new JSONObject();

        try {
            json.put("password", password);
            json.put("newPassword", newPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("TAG", "verifyEmail: " + json );

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/password/change")
                .addHeader("Authorization", "Bearer "+token)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                instance.run(() -> {
                    baseCallBack.onFailure(e.getMessage());
                    baseCallBack.onDone();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                instance.run(() -> {
                    if(response.code() == 200){
                        baseCallBack.onSuccess(true);
                    }else
                        baseCallBack.onSuccess(false);
                    baseCallBack.onDone();
                });
            }
        });
    }

    public void recoverPassword(String email, String otp, String newPassword, BaseCallBack<Boolean> baseCallBack){

        JSONObject json = new JSONObject();

        try {
            json.put("email", email);
            json.put("otp", otp);
            json.put("newPassword", newPassword);
        } catch (JSONException e) {
            Log.e("TAG", "verifyEmail: " + e.getMessage() );
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/password/recover")
                .post(body)
                .build();
        Log.e("TAG", "verifyEmail: " + json.toString() );

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                instance.run(() -> {
                    baseCallBack.onFailure(e.getMessage());
                    baseCallBack.onDone();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                instance.run(() -> {
                    Log.e("TAG", "verifyEmail: " + response );
                    if(response.code() == 200){
                        baseCallBack.onSuccess(true);
                    }else
                        baseCallBack.onSuccess(false);
                    baseCallBack.onDone();
                });

            }
        });
    }

    public void getModifiedDate(String token, BaseCallBack<Boolean> baseCallBack){

        Request request = new Request.Builder()
                .url(URL + API + "/modified-date")
                .addHeader("Authorization", "Bearer "+token)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                instance.run(() -> {
                    baseCallBack.onFailure(e.getMessage());
                    baseCallBack.onDone();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                instance.run(() -> {
                    if(response.code() == 200){
                        baseCallBack.onSuccess(true);
                    }else
                        baseCallBack.onSuccess(false);
                    baseCallBack.onDone();
                });

            }
        });
    }

}
