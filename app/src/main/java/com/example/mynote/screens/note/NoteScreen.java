package com.example.mynote.screens.note;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
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


        binding.saveButton.setOnClickListener(view1 -> {
            Intent i = new Intent();
            i.putExtra("sample_email", "binding.editText.getText().toString()");
            setResult(Activity.RESULT_OK, i);
            finish();
        });

    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}