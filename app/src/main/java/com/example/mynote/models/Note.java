package com.example.mynote.models;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;
import java.util.List;

public class Note implements Parcelable {
    private String title;
    private String message;
    private String dateNotify; // '21/5/2022 22:45' like this
    private String password;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Note(String title, String message, String dateNotify, String password, String tag) {
        this.title = title;
        this.message = message;
        this.dateNotify = dateNotify;
        this.password = password;
        this.tag = tag;
    }

    public String getDateNotify() {
        return dateNotify;
    }

    public void setDateNotify(String dateNotify) {
        this.dateNotify = dateNotify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected Note(Parcel in) {
        title = in.readString();
        message = in.readString();
        dateNotify = in.readString();
        password = in.readString();
        tag = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Note{"+ title + ", message: " + message + ", dateNotify: " + dateNotify + ", password: " + password + "tag: " + tag +'}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(message);
        parcel.writeString(dateNotify);
        parcel.writeString(password);
        parcel.writeString(tag);
    }
}
