package com.example.mynote.models;

import android.util.Log;

public class User {
    private String user;
    private String email;
    private UserType type;
    private boolean isVerified;

    public User(){
        this.user = "";
        this.email = "";
        this.type = UserType.GUEST;
        this.isVerified = false;
    }

    public User(String user, String email, UserType userType, boolean verify){
        this.user = user;
        this.email = email;
        type = userType;
        isVerified = verify;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
