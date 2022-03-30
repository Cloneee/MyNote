package com.example.mynote.screens.home;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.mynote.R;
import com.example.mynote.adapter.NoteAdapter;
import com.example.mynote.databinding.FragmentHomeBinding;
import com.example.mynote.models.ArrayObserver;
import com.example.mynote.models.Note;
import com.example.mynote.screens.note.NoteScreen;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment{

    private FragmentHomeBinding binding;

    // that is what we gonna show
    private ArrayList<Note> notes;
    //that is all we got
    private ArrayList<Note> noteList;
    private NoteAdapter noteAdapter;
    private Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        initNotes();
        noteAdapter = new NoteAdapter(this.getContext(), notes);
        noteAdapter.observer = () -> {
            if(notes.isEmpty()) {
                binding.listItem.setVisibility(View.GONE);
                binding.listItemEmpty.setVisibility(View.VISIBLE);
            }else{
                binding.listItem.setVisibility(View.VISIBLE);
                binding.listItemEmpty.setVisibility(View.GONE);
            }
        };
        noteAdapter.registerDataSetObserver();

        binding.listItemEmpty.setVisibility(View.GONE);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    try {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Handle the Intent
                            Intent intent = result.getData();
                            String data = intent.getStringExtra("sample_email");

                            //do what you want
                            if(data == null || data.isEmpty()) {}
                            else{
                                Log.e("TAG", "onActivityResult: " + data.toString());
                            }

                        }
                    }catch (Exception e){

                    }

                }
            });

        binding.listView.setAdapter(noteAdapter);

        binding.listView.setOnItemClickListener((AdapterView<?> adapterView, View view1, int position, long id) -> {
            try{
                Note note = notes.get(position);
                String message = "You've clicked on " + note.getName();
                if(toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG);
                toast.show();

                Log.e("TAG", message);
            }catch (Exception e){
                Log.e("errorTAG", "onViewCreated: " + e);
            }
        });

        binding.newButton.setOnClickListener(view1 -> {
            //Intent i = new Intent(getContext(), NoteScreen.class);
            //mStartForResult.launch(i);


        });

//        searchBox.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                students.clear();
//                if(searchBox.getText().toString().isEmpty()){
//                    students.addAll(studentsList);
//                }else {
//                    students.addAll(studentsList.stream().filter(p -> p.getName().toLowerCase().contains(searchBox.getText().toString().toLowerCase())).collect(Collectors.toList()));
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//        });
    }

    private void initNotes() {
        noteList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {

            String title = "Student " + i;
            String message = "student" + i + "@gmail.com";

            noteList.add(new Note(title, message, new ArrayList<Integer>()));
        }

        notes = (ArrayList<Note>) noteList.clone();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}