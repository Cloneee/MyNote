package com.example.mynote.models;

public class LoginResponse {
    public String username;
    public String email;
    public String token;
    public boolean verify;

    public LoginResponse(){
        this.username = "";
        this.email = "";
        this.token = "";
        this.verify = false;
    }

    public LoginResponse(LoginResponse res){
        this.username = res.username;
        this.email = res.email;
        this.token = res.token;
        this.verify = res.verify;
    }
}
