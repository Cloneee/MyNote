package com.example.mynote.components;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    DatePickerCallback finish;

    int year;
    int month;
    int day;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public  DatePickerFragment(){
        this.year = 0;
        this.month = 0;
        this.day = 0;
    }

    public  DatePickerFragment(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(this.year != 0){
            year = this.year;
        }
        if(this.month != 0){
            month = this.month;
        }
        if(this.day != 0){
            day = this.day;
        }

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        if(finish != null) finish.onCallBack(year, month, day);
    }

    public void setOnDatePickerCall (DatePickerCallback f){
        finish = f;
    }
}
