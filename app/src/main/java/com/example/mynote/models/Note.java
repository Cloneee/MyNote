package com.example.mynote.models;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Note implements Parcelable {
    private String id;
    private String title;
    private String message;
    private String dateNotify; // '21/5/2022 22:45' like this
    private String password;
    private String tagId;

    public Note(String title, String message, String dateNotify, String password, String tagId,  String id) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.dateNotify = dateNotify;
        this.password = password;
        this.tagId = tagId;
    }

    public String getId() {
        return id;
    }

    public String getTagId() {
        return tagId;
    }

    public Note(String title, String message, String dateNotify, String password, String tagId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.message = message;
        this.dateNotify = dateNotify;
        this.password = password;
        this.tagId = tagId;
    }

    public String getTag() {
        return tagId;
    }

    public void setTag(String tagId) {
        this.tagId = tagId;
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
        id = in.readString();
        title = in.readString();
        message = in.readString();
        dateNotify = in.readString();
        password = in.readString();
        tagId = in.readString();
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
        return "Note{"+ title + ", id: " + id + ", message: " + message + ", dateNotify: " + dateNotify + ", password: " + password + "tagId: " + tagId +'}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(message);
        parcel.writeString(dateNotify);
        parcel.writeString(password);
        parcel.writeString(tagId);
    }
}
