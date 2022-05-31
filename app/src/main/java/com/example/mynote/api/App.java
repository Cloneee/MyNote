package com.example.mynote.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynote.interfaces.BaseCallBack;
import com.example.mynote.models.LoginResponse;
import com.example.mynote.models.Note;
import com.example.mynote.models.NoteResponse;
import com.example.mynote.services.s.RunOnUIHelper;
import com.squareup.moshi.JsonAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSource;

public class App extends Base {

    private App(){}

    private static App app;

    public static App getApp(){
        if(app == null){
            app = new App();
        }
        return app;
    }

    public String getToken (){
        return token;
    }

    private static String API = "/note";

    private static final ArrayList<NoteResponse> noteList = new ArrayList<NoteResponse>();

    private static final JsonAdapter<NoteResponse> noteResponseJsonAdapter = moshi.adapter(NoteResponse.class);
    private static final JsonAdapter<Note> noteJsonAdapter = moshi.adapter(Note.class);

    private RunOnUIHelper instance = RunOnUIHelper.getInstance();

    String token = "";

    public void setToken(String t){
        token = t;
    }

    public void getNotes(BaseCallBack callBack){
        Request request = new Request.Builder()
                .url(URL + API)
                .addHeader("Authorization", "Bearer " + token)
                .get()
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
                    ArrayList<Note> res = new ArrayList<>();

                    JSONArray array = new JSONArray(response.body().string());

                    Log.e("TAG", "notes: " + array);

                    for (int i = 0; i < array.length(); i++){
                        res.add(new Note(array.getJSONObject(i).toString()));
                    }

                    callBack.onSuccess(res);
                    callBack.onDone();

                });
            }
        });

    }

    public void getNoteById(String id){
        Request request = new Request.Builder()
                .url(URL + API + "/" + id)
                .addHeader("Authorization", "Bearer "+token)
                .get()
                .build();

        RunOnUIHelper instance = RunOnUIHelper.getInstance();

        try{
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    instance.run(() -> {
                        // TODO Callback here

                    });
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    instance.run(() -> {
                        NoteResponse res = null;
                        try {
                            Log.e("TAG", "onResponse: " + response.toString());
                            // TODO Get response
                            res = noteResponseJsonAdapter.fromJson(response.body().source());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // TODO Callback here

                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNote(Note note, BaseCallBack<Boolean> callBack){
        String json = noteJsonAdapter.toJson(note);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API)
                .addHeader("Authorization", "Bearer "+token)
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

                    Log.e("TAG", "onResponse: " + response.body().source().toString());

                    NoteResponse res = new NoteResponse(noteResponseJsonAdapter.fromJson(response.body().source()));

                    if(response.code() == 200)
                        callBack.onSuccess(true);
                    else
                        callBack.onFailure("Cannot create, try again latter");
                    // TODO Callback here
                    callBack.onDone();
                });
            }
        });

    }

    public void editNote(String id, Note note, BaseCallBack<Boolean> callBack){
        String json = noteJsonAdapter.toJson(note);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API + "/" + id)
                .addHeader("Authorization", "Bearer "+token)
                .put(body)
                .build();

        RunOnUIHelper instance = RunOnUIHelper.getInstance();
        try{
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
                        if(response.code() == 200){
                            callBack.onSuccess(true);
                        }else {
                            callBack.onSuccess(false);
                        }

                        callBack.onDone();
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNote(String id, BaseCallBack<Boolean> callBack){
        Request request = new Request.Builder()
                .url(URL + API + "/" + id)
                .addHeader("Authorization", "Bearer "+token)
                .delete()
                .build();

        RunOnUIHelper instance = RunOnUIHelper.getInstance();
        try{
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
                        if(response.code() == 200){
                            callBack.onSuccess(true);
                        }else {
                            callBack.onSuccess(false);
                        }

                        callBack.onDone();
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteAllNotes(){
        Request request = new Request.Builder()
                .url(URL + API)
                .addHeader("Authorization", "Bearer "+token)
                .delete()
                .build();

        RunOnUIHelper instance = RunOnUIHelper.getInstance();
        try{
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    instance.run(() -> {
                        // TODO Callback here

                    });
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    instance.run(() -> {
                        String res = null;
                        try {
                            Log.e("TAG", "onResponse: " + response.toString());
                            res = response.body().toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // TODO Callback here

                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
