package com.example.mynote.interfaces;

import java.util.Date;

public class NoteResponse {
    public String id;
    public String title;
    public String message;
    public String tagId;
    public Boolean isPin;
    public String dateNotify;
    public String password;
    public Date dateCreated;
    public String user;

    public NoteResponse(NoteResponse res) {
        this.id = res.id;
        this.title = res.title;
        this.message = res.message;
        this.tagId = res.tagId;
        this.isPin = res.isPin;
        this.dateNotify = res.dateNotify;
        this.password = res.password;
        this.dateCreated = res.dateCreated;
        this.user = res.user;
    }
}
