package com.example.mynote.interfaces;

import com.example.mynote.models.LoginResponse;

public interface CustomCallBack{
    void run(Object res);
    default void run2(Object res){};
}
