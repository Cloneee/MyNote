package com.example.mynote.screens.note;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mynote.components.*;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.mynote.R;
import com.example.mynote.configs.Constant;
import com.example.mynote.databinding.ActivityNoteScreenBinding;
import com.example.mynote.models.Note;


public class NoteScreen extends AppCompatActivity {
    ActivityNoteScreenBinding binding;
    TimePickerFragment timePickerDialog = new TimePickerFragment();
    DatePickerFragment datePickerDialog = new DatePickerFragment();

    String password = "";
    String dateNotify = "";
    String timeNotify = "";
    String tag = "";

    PopupWindow popupWindow;

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
                dateNotify = day + ":" + month + ":" + year;
                timePickerDialog.show(getSupportFragmentManager(), "Time");
            });

        }catch (Exception e){
            Log.e("TAG", e.toString() );
        }

        binding = ActivityNoteScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.addNotify.setOnClickListener(view1 -> {
            try{
                datePickerDialog.show(getSupportFragmentManager(), "Time");
            }catch (Exception e){
                Log.e("TAG", e.toString() );
            }
        });

        binding.addTag.setOnClickListener(view1 -> {
            try{
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

            String notify = "";
            if(!dateNotify.isEmpty() && !timeNotify.isEmpty()){
                notify = dateNotify + " " + timeNotify;
            }

            Intent i = new Intent();
            i.putExtra(Constant.NOTE_RESULT, new Note(binding.title.getText().toString(), binding.message.getText().toString(), notify, password, tag));
            setResult(Activity.RESULT_OK, i);
            finish();
        });

    }

    public void onAddPasswordClicked(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.password_popup, null);

        //get element
        Button addPassword = popupView.findViewById(R.id.add_password);
        EditText passwordBox = popupView.findViewById(R.id.note_password);

        passwordBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = ""+s;
            }
        });

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        if(popupWindow == null) popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

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