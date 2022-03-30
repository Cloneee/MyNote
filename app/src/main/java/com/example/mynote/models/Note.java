package com.example.mynote.models;


import java.util.List;

public class Note {
    private String title;
    private String message;
    private List<Integer> attachments;

    public Note(String title, String message, List<Integer> attachments) {
        this.title = title;
        this.message = message;
        this.attachments = attachments;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getEmail() {
        return message;
    }

    public void setEmail(String message) {
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
}
