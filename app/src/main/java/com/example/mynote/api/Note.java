package com.example.mynote.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynote.interfaces.LoginResponse;
import com.example.mynote.interfaces.NoteResponse;
import com.example.mynote.services.s.RunOnUIHelper;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSource;

public class Note extends Base {
    private static String URL = "https://android-server-781.herokuapp.com";
    private static String API = "/note";
    private static Auth auth = Auth.getAuth();
    private String token = auth.getToken();
    private static final ArrayList<NoteResponse> noteList = new ArrayList<NoteResponse>();
    private static final Note note = new Note();

    private static final JsonAdapter<NoteResponse> noteResponseJsonAdapter = moshi.adapter(NoteResponse.class);
    private static final JsonAdapter<Note> noteJsonAdapter = moshi.adapter(Note.class);

    private Note(){}
    public void getNotes(){
        Request request = new Request.Builder()
                .url(URL + API)
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
                        ArrayList<NoteResponse> res = null;
                        try {
                            Log.e("TAG", "onResponse: " + response.toString());
                            JSONArray myResponse = new JSONArray(response.body());
                            for (int i = 0; i< myResponse.length(); i++){
                                NoteResponse cursorNote = noteResponseJsonAdapter.fromJson((BufferedSource) myResponse.getJSONObject(i));
                                noteList.add(cursorNote);
                            }
                        } catch (IOException | JSONException e) {
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
    public void createNote(Note note){
        String json = noteJsonAdapter.toJson(note);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL + API)
                .addHeader("Authorization", "Bearer "+token)
                .post(body)
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
                            res = new NoteResponse(noteResponseJsonAdapter.fromJson(response.body().source()));
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
    public void editNote(String id, Note note){
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
                            res = new NoteResponse(noteResponseJsonAdapter.fromJson(response.body().source()));
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
    public void deleteNote(String id){
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
