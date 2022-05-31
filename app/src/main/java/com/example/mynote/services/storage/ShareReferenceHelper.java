package com.example.mynote.services.storage;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mynote.base.BaseClass;
import com.example.mynote.configs.Constant;
import com.example.mynote.models.Note;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShareReferenceHelper {
    private static final ShareReferenceHelper SINGLE_INSTANCE = new ShareReferenceHelper();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private ShareReferenceHelper() {}

    public static ShareReferenceHelper getInstance() {
        return SINGLE_INSTANCE;
    }


    public void init(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    public boolean storeNoteArray(String key, ArrayList<Note> arr){
        try{
            ArrayList<String> temp = new ArrayList<>();
            for (Note i: arr) {
                temp.add(i.toJson());
            }

            Set<String> data = new HashSet<String>(temp);
            editor.putStringSet(Constant.PREFIX_STORAGE + key, data);
            editor.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public ArrayList<Note> getNoteArray(String key){
        try{
            Set<String> data = sharedPreferences.getStringSet(Constant.PREFIX_STORAGE + key, null);

            ArrayList<Note> result = new ArrayList<>();

            for (String i: data) {
                result.add(new Note(i));
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }


    public boolean storeString(String key, String data){
        try{
            Log.e("TAG", "v: " +key + " "+ data );
            editor.putString(Constant.PREFIX_STORAGE + key, data);
            editor.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getString(String key){
        try{
            return sharedPreferences.getString(Constant.PREFIX_STORAGE + key, "");
        }catch (Exception e){
            return "";
        }
    }

    public boolean deleteData(String key){
        try{
            editor.remove(Constant.PREFIX_STORAGE + key);
            editor.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
