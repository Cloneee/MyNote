package com.example.mynote.services.s;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class RunOnUIHelper {
    private static RunOnUIHelper INSTANCE;

    private RunOnUIHelper() {
    }

    public static RunOnUIHelper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RunOnUIHelper();
        }
        return INSTANCE;
    }

    private static Activity activity;

    public static void init(Activity activity1) {
        activity = activity1;
    }

    public void run(RunOnUiInterface runOnUiInterface){
        activity.runOnUiThread(() -> {
            try {
                runOnUiInterface.run();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("TAG", "run: " + e.getMessage() );
            }
        });
    }
}
