package com.example.mynote.api;

import com.squareup.moshi.Moshi;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class Base
{
    static String URL = "https://android-server-781.herokuapp.com/api/v1";
    static final Moshi moshi = new Moshi.Builder().build();
    static final OkHttpClient client = new OkHttpClient();
    static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
}
