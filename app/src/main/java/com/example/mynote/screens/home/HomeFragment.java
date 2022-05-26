package com.example.mynote.screens.home;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.example.mynote.configs.Constant.NOTE_RESULT;

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

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.mynote.R;
import com.example.mynote.adapter.NoteAdapter;
import com.example.mynote.configs.Constant;
import com.example.mynote.configs.ToastHelper;
import com.example.mynote.databinding.FragmentHomeBinding;
import com.example.mynote.models.ArrayObserver;
import com.example.mynote.models.Note;
import com.example.mynote.screens.note.NoteScreen;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class HomeFragment extends Fragment{

    private FragmentHomeBinding binding;

    // that is what we gonna show
    private ArrayList<Note> notes;
    //that is all we got
    private ArrayList<Note> noteList;
    private NoteAdapter noteAdapter;

    PopupWindow passwordPopupWindow;
    String password;

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
//        noteAdapter.notifyDataSetChanged();
        noteAdapter.onDataChange();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.listView.setAdapter(noteAdapter);

        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    try {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Handle the Intent
                            Intent intent = result.getData();
                            Note noteResult = (Note) intent.getParcelableExtra(NOTE_RESULT);

                            if(noteResult == null) return;
                            Log.e("TAG", "onActivityResult: " + noteResult.toString());

                            //do what you want

                            if(isNoteExist(noteResult)){
                                Note n = getNoteInArray(noteResult.getId());
                                changeNote(n, noteResult);
                            } else {
                                addNote(noteResult.getTitle(), noteResult.getMessage(), noteResult.getDateNotify(), noteResult.getPassword(), noteResult.getTag());
                            }



                        }
                    }catch (Exception e){
                        Log.e("TAG", "error: " + e);
                    }

                });

        binding.listView.setOnItemClickListener((AdapterView<?> adapterView, View view1, int position, long id) -> {
            try{
                Note note = notes.get(position);
                if(!note.getPassword().isEmpty()){

                }
                Intent i = new Intent(getContext(), NoteScreen.class);
                i.putExtra(Constant.NOTE_RESULT, note);
                mStartForResult.launch(i);
            }catch (Exception e){
                Log.e("errorTAG", "onViewCreated: " + e);
            }
        });
        binding.listView.setOnItemLongClickListener((adapterView, view1, i, l) -> {
            return true;
        });


        binding.newButton.setOnClickListener(view1 -> {
            Intent i = new Intent(getContext(), NoteScreen.class);
            mStartForResult.launch(i);
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
        for (int i = 1; i <= 0; i++) {
            String title = "Student " + i;
            String message = "student" + i + "@gmail.com";
            noteList.add(new Note(title, message, "", "", ""));
        }
        notes = (ArrayList<Note>) noteList.clone();
    }

    private void addNote (String title, String message, String dateNotify, String password, String tag){
        try {
            noteList.add(new Note(title, message,  dateNotify, password, tag));
            notes.clear();
            notes.addAll(noteList);
            noteAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Log.e("TAG", "onViewCreated: " + e);
        }
    }

    private void changeNote (Note note, Note targetNote){
        try {
            note.setTitle(targetNote.getTitle());
            note.setMessage(targetNote.getMessage());
            note.setPassword(targetNote.getPassword());
            note.setDateNotify(targetNote.getDateNotify());
            note.setTag(targetNote.getTag());

            noteAdapter.notifyDataSetChanged();

        }catch (Exception e){
            Log.e("TAG", "onViewCreated: " + e);
        }
    }

    private boolean isNoteExist(Note note){
        for (Note n: notes) {
            if(note.getId().equals(n.getId())) return true;
        }
        return false;
    }

    private Note getNoteInArray(String id){
        for (Note n: notes) {
            if(id.equals(n.getId())) return n;
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void onCheckPasswordClicked(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.check_password_popup, null);

        //get element
        EditText passwordBox = popupView.findViewById(R.id.note_password);
        passwordBox.setText(password);

        popupView.findViewById(R.id.check_password_button).setOnClickListener(view1 -> {

        });

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


}