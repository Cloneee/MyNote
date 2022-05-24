package com.example.mynote.configs;

import com.example.mynote.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Constant {
    public static String NOTE_RESULT = "NOTE_RESULT";
    public static NoteTag[] noteTagList = {
            new NoteTag("01", "normal", R.drawable.ic_normal),
            new NoteTag("02", "importance", R.drawable.ic_star_note),
            new NoteTag("03", "notify", R.drawable.ic_notify_note),
            new NoteTag("04", "travel", R.drawable.ic_travel),
            new NoteTag("05", "weather", R.drawable.ic_weather),
            new NoteTag("06", "school", R.drawable.ic_school),
            new NoteTag("07", "shopping", R.drawable.ic_shopping),

    };
}