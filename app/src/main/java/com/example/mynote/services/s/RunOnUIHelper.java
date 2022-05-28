package com.example.mynote.services.s;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

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
        activity.runOnUiThread(() -> runOnUiInterface.run());
    }
}
