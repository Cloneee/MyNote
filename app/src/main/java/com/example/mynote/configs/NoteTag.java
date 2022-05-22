package com.example.mynote.configs;

public class NoteTag {
    private String id;
    private String name;
    private int tagIconId;

    public String getId() {
        return id;
    }

    public NoteTag(String id, String name, int tagIconId) {
        this.id = id;
        this.name = name;
        this.tagIconId = tagIconId;
    }

    public NoteTag(NoteTag noteTag) {
        this.id = noteTag.id;
        this.name = noteTag.name;
        this.tagIconId = noteTag.tagIconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTagIconId() {
        return tagIconId;
    }

    public void setTagIconId(int tagIconId) {
        this.tagIconId = tagIconId;
    }
}
