package com.example.mynote.screens.note;

import static com.example.mynote.configs.Constant.NOTE_RESULT;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mynote.components.*;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.mynote.R;
import com.example.mynote.configs.Constant;
import com.example.mynote.configs.NoteTag;
import com.example.mynote.configs.ToastHelper;
import com.example.mynote.databinding.ActivityNoteScreenBinding;
import com.example.mynote.models.Note;

import java.util.ArrayList;


public class NoteScreen extends AppCompatActivity {
    ActivityNoteScreenBinding binding;
    TimePickerFragment timePickerDialog = new TimePickerFragment();
    DatePickerFragment datePickerDialog = new DatePickerFragment();

    String id = "";
    String password = "";
    String dayNotify = "";
    String timeNotify = "";
    String tag = "01";
    String date = "";

    PopupWindow passwordPopupWindow;
    PopupWindow tagPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_note_screen);

        try {
            timePickerDialog.setOnTimePickerCall((hourOfDay, minute) -> {
                timeNotify = hourOfDay + ":" + minute;
            });
            datePickerDialog.setOnDatePickerCall((year, month, day) -> {
                dayNotify = day + ":" + month + ":" + year;
                timePickerDialog.show(getSupportFragmentManager(), "Time");
            });

        }catch (Exception e){
            Log.e("TAG", e.toString() );
        }

        binding = ActivityNoteScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // init data get from home when you want change
        Intent intent = getIntent();
        try {
            initData(intent);
        }catch (Exception e){
            Log.e("TAG", e.toString() );
        }

        binding.backButton.setOnClickListener(view1 -> {
            finish();
        });

        binding.addNotify.setOnClickListener(view1 -> {
            try{
                datePickerDialog.show(getSupportFragmentManager(), "Time");
            }catch (Exception e){
                Log.e("TAG", e.toString() );
            }
        });

        binding.addTag.setOnClickListener(view1 -> {
            try{
                onAddTagClicked(view1);
            }catch (Exception e){
                Log.e("TAG", e.toString() );
            }
        });

        binding.addPassword.setOnClickListener(view1 -> {
            try{
                onAddPasswordClicked(view1);
            }catch (Exception e){
                Log.e("TAG", e.toString() );
            }
        });

        binding.saveButton.setOnClickListener(view1 -> {

            if(binding.title.getText().toString().isEmpty() && binding.message.getText().toString().isEmpty()){
                ToastHelper.showToast("Note can't be empty");
                return;
            }

            String notify = "";
            if(!dayNotify.isEmpty() && !timeNotify.isEmpty()){
                notify = dayNotify + " " + timeNotify;
            }

            if(notify.isEmpty()) notify = date;
            Intent i = new Intent();

            if(id.isEmpty()){
                i.putExtra(Constant.NOTE_RESULT, new Note(binding.title.getText().toString(), binding.message.getText().toString(), notify, password, tag));
                setResult(Activity.RESULT_OK, i);
            }else {
                i.putExtra(Constant.NOTE_RESULT, new Note(binding.title.getText().toString(), binding.message.getText().toString(), notify, password, tag, id));
                setResult(Activity.RESULT_OK, i);
            }

            finish();
        });

    }

    void initData (Intent intent) {
        Note noteResult = (Note) intent.getParcelableExtra(NOTE_RESULT);
        if(noteResult != null){
            id = noteResult.getId();
            password = noteResult.getPassword();
            date = noteResult.getDateNotify();
            tag = noteResult.getTagId();
            Log.e("TAG", "initData: " + tag);
            binding.title.setText(noteResult.getTitle());
            binding.message.setText(noteResult.getMessage());
        }
    }

    void getDate (String date){

    }

    public void onAddTagClicked(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.tag_popup, null);

        WrappedContent tagList = popupView.findViewById(R.id.tag_list_container);
        if(tagList == null) return;

        for (NoteTag tag : Constant.noteTagList) {
            ImageButton imageButton = new ImageButton(view.getContext());
            imageButton.setMinimumWidth(100);
            imageButton.setImageResource(tag.getTagIconId());
            imageButton.setColorFilter(this.tag.equals(tag.getId()) ? getResources().getColor(R.color.teal_200) : getResources().getColor(R.color.background));
            imageButton.setBackgroundColor(getResources().getColor(R.color.transparent));
            imageButton.setOnClickListener(view1 -> {
                this.tag = tag.getId();
                if(tagPopupWindow != null) tagPopupWindow.dismiss();
                tagPopupWindow = null;
            });
            tagList.addView(imageButton);
        }

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        tagPopupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        tagPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void onAddPasswordClicked(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.password_popup, null);

        //get element
        EditText passwordBox = popupView.findViewById(R.id.note_password);
        passwordBox.setText(password);

        passwordBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = passwordBox.getText().toString();
            }
        });

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        if(passwordPopupWindow == null) passwordPopupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        passwordPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }

}