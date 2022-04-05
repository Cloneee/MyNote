package com.example.mynote.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Note implements Parcelable {
    private String title;
    private String message;
    private List<Integer> attachments;

    public Note(String title, String message, List<Integer> attachments) {
        this.title = title;
        this.message = message;
        this.attachments = attachments;
    }

    protected Note(Parcel in) {
        title = in.readString();
        message = in.readString();
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

    public List<Integer> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Integer> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "Student{"+ title + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(message);
    }
}
