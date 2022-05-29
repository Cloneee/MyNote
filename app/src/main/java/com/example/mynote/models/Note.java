package com.example.mynote.models;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.mynote.base.BaseClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Note extends BaseClass implements Parcelable {
    private String id;
    private String title;
    private String message;
    private String dateNotify; // '21/5/2022 22:45' like this
    private String password;
    private String tagId;

    public static String ID = "id";
    public static String TITLE = "title";
    public static String MESSAGE = "message";
    public static String DATE_NOTIFY = "dateNotify";
    public static String PASSWORD = "password";
    public static String TAG_ID = "tagId";

    public Note(String title, String message, String dateNotify, String password, String tagId,  String id) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.dateNotify = dateNotify;
        this.password = password;
        this.tagId = tagId;
    }

    public Note(String title, String message, String dateNotify, String password, String tagId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.message = message;
        this.dateNotify = dateNotify;
        this.password = password;
        this.tagId = tagId;
    }

    public Note(String json) throws JSONException {
            if(json.isEmpty()) return;

            JSONObject  newJObject = new JSONObject(json);

            this.id = newJObject.getString(ID);
            this.title = newJObject.getString(TITLE);
            this.message = newJObject.getString(MESSAGE);
            this.dateNotify = newJObject.getString(DATE_NOTIFY);
            this.password = newJObject.getString(PASSWORD);
            this.tagId = newJObject.getString(TAG_ID);


    }

    public Note(Note note) {
        this.id = note.id;
        this.title = note.title;
        this.message = note.message;
        this.dateNotify = note.dateNotify;
        this.password = note.password;
        this.tagId = note.tagId;
    }

    public String getId() {
        return id;
    }

    public String getTagId() {
        return tagId;
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

    public String toJson(){
        try{
            JSONObject jObject = new JSONObject();

            jObject.put(ID, id);
            jObject.put(TITLE, title);
            jObject.put(MESSAGE, message);
            jObject.put(DATE_NOTIFY, dateNotify);
            jObject.put(PASSWORD, password);
            jObject.put(TAG_ID, tagId);

            return jObject.toString();

        }catch (Exception e){
            return  null;
        }
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
