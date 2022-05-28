package com.example.mynote.interfaces;

public class LoginResponse {
    public String username;
    public String token;

    public LoginResponse(){
        this.username = "";
        this.token = "";
    }

    public LoginResponse(LoginResponse res){
        this.username = res.username;
        this.token = res.token;
    }
}
