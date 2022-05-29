package com.example.mynote.models;

public class User {
    private String user;
    private UserType type;

    public User(){
        user = "";
        type = UserType.GUEST;
    }

    public User(String user){
        this.user = user;
        type = UserType.NORMAL;
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

}
