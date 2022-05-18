package com.example.mynote.screens.note;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mynote.components.*;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.mynote.R;
import com.example.mynote.databinding.ActivityNoteScreenBinding;


public class NoteScreen extends AppCompatActivity {
    ActivityNoteScreenBinding binding;
    TimePickerFragment timePickerDialog = new TimePickerFragment();
    DatePickerFragment datePickerDialog = new DatePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_note_screen);

        try {

            timePickerDialog.setOnTimePickerCall((hourOfDay, minute) -> {
            });
            datePickerDialog.setOnDatePickerCall((year, month, day) -> {
            });

        }catch (Exception e){
            Log.e("TAG", e.toString() );
        }

        binding = ActivityNoteScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.calendarButton.setOnClickListener(view1 -> {
            try{

                Log.e("TAG", "presssss" );
                datePickerDialog.show(getSupportFragmentManager(), "Time");
                timePickerDialog.show(getSupportFragmentManager(), "Time");
//                onButtonShowPopupWindowClick(view1);
            }catch (Exception e){
                Log.e("TAG", e.toString() );
            }
        });

//        binding.saveButton.setOnClickListener(view1 -> {
//            Intent i = new Intent();
//            i.putExtra(Constant.NOTE_RESULT, new Note(binding.title.getText().toString(),binding.message.getText().toString(), new ArrayList<Integer>()));
//            setResult(Activity.RESULT_OK, i);
//            finish();
//        });

    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.calendar_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

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