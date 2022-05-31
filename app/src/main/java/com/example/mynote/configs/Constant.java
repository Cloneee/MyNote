package com.example.mynote.configs;

import com.example.mynote.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Constant {
    public static String NOTE_RESULT = "NOTE_RESULT";

    public static String OTP_RESULT = "OTP_RESULT";

    public static String OTP_MAIL = "OTP_MAIL";
    public static String OTP_VALUE = "OTP_VALUE";

    public static String PASSWORD_SCREEN_TYPE = "PASSWORD_SCREEN_TYPE";


    public static String NOTE_LIST_REFERENCE = "NOTE_LIST_REFERENCE";

    public static String PREFIX_STORAGE = "MY_NOTE_";

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