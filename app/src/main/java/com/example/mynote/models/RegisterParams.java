package com.example.mynote.models;

public class RegisterParams {
    public String username;
    public String password;
    public String email;

    public RegisterParams(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public RegisterParams(){
        this("", "", "");
    }
}
