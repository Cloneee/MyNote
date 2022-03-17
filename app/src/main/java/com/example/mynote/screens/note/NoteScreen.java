package com.example.mynote.screens.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mynote.R;
import com.example.mynote.databinding.ActivityNoteScreenBinding;


public class NoteScreen extends AppCompatActivity {
    ActivityNoteScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyNote);
        setContentView(R.layout.activity_note_screen);

        binding = ActivityNoteScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}