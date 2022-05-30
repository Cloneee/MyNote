package com.example.mynote.models;

public class User {
    private String user;
    private UserType type;
    private boolean isVerified;

    public User(){
        new User("", UserType.GUEST, false);
    }

    public User(String user, UserType userType, boolean verify){
        this.user = user;
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
}
