package com.example.mynote.api;
import com.example.mynote.interfaces.Login;
import com.example.mynote.interfaces.LoginResponse;
import com.example.mynote.interfaces.Register;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Auth {
    private static String URL = "https://android-server-781.herokuapp.com";
    private static String API = "/api/user";
    private String token = "";
    private static final Auth auth = new Auth();
    private static final Moshi moshi = new Moshi.Builder().build();
    private static final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final JsonAdapter<LoginResponse> loginResponseAdapter = moshi.adapter(LoginResponse.class);
    private static final JsonAdapter<Login> loginAdapter = moshi.adapter(Login.class);
    private static final JsonAdapter<Register> registerAdapter = moshi.adapter(Register.class);

    private Auth(){}
    public static Auth getAuth(){
        return auth;
    }

    public LoginResponse login(Login login){
        String json = loginAdapter.toJson(login);
        RequestBody body = RequestBody.create(json, JSON);
        LoginResponse result;
        Request request = new Request.Builder()
                .url(URL + API + "/login")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            result = loginResponseAdapter.fromJson(response.body().source());
            token = result.token;
        } catch (IOException e) {
            result = new LoginResponse();
            e.printStackTrace();
        }
        return result;
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
